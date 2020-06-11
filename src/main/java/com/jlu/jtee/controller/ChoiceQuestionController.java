package com.jlu.jtee.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.domain.Exam;
import com.jlu.jtee.domain.Option;
import com.jlu.jtee.service.ChoiceQuestionService;
import com.jlu.jtee.service.ExamService;
import com.jlu.jtee.service.StudentService;
import com.jlu.jtee.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/choice")
public class ChoiceQuestionController {
    private final static Logger logger = LoggerFactory.getLogger(ChoiceQuestionController.class);


    @Autowired
    ChoiceQuestionService choiceQuestionService;

    @Autowired
    ExamService examService;

    @Autowired
    StudentService studentService;

    @GetMapping("getTypelist")
    public String getTypeList(Model model){
        List<String> list = choiceQuestionService.getTypeList();
        model.addAttribute("TypeList",list);
        return "javaExm";
    }

    @GetMapping("getListByType")
    public String getListByType(@RequestParam("type")String type, Model model, HttpSession session){
        List<ChoiceQuestion> list = choiceQuestionService.getListByType(type);
        String UserName = (String)session.getAttribute("loginUser");
        Integer sId = studentService.findIdByUserName(UserName);
        Exam exam = new Exam();
        Date date = new Date();
        exam.setCreateTime(date);
        exam.setEndTime(new Date(date.getTime() + 1*60*60*1000));
        exam.setsId(sId);
        exam.setType(type);
        exam.setStatus("考试中");
        examService.newExam(exam);
        logger.info("新建考试");
        model.addAttribute("Question",list);
        return "javaExmDetail";
    }

    @PostMapping("checkAnswer")
    @ResponseBody
    public String checkAnswer(HttpServletRequest request,HttpSession session){
        ArrayList<String> daAn = new ArrayList<String>();
        ArrayList<Option> ti = new ArrayList<>();
        String ds = request.getParameter("postData");//字符串
        JSONArray jsonArray = JSONArray.parseArray(ds);
        int Tcount = 0;
        int Fcount = 0;
        for (int i=0;i<jsonArray.size();i++){
            String TiHao = jsonArray.getJSONObject(i).getString("TiHao");
            String answer = choiceQuestionService.getAnswerById(Integer.valueOf(TiHao));
            Option option = new Option(TiHao,answer);
            ti.add(option);
        }
        for (int i=0;i<jsonArray.size();i++){
            String TiHao = jsonArray.getJSONObject(i).getString("TiHao");
            String answer = choiceQuestionService.getAnswerById(Integer.valueOf(TiHao));
            int length =  jsonArray.getJSONObject(i).getString("XuanXiang").length();
            List la = new ArrayList();
            List lb = new ArrayList();
            for (int j=0;j<answer.length()-1;j++){
                char b = answer.charAt(j);
                if (b>='A'&&b<='Z')
                  lb.add(b);
            }
            for (int j=0;j<length-1;j++)
            {
                char a = jsonArray.getJSONObject(i).getString("XuanXiang").charAt(j);
                if(a>='A'&&a<='Z')
                    la.add(a);
            }
            if (la.containsAll(lb)){
                Tcount++;
            }else{
                Fcount++;
            }
        }
        logger.info("查找答案");
        logger.info("正确："+Tcount+"错误："+Fcount);
       // logger.info(ti.toString());
        //提交后同时修改成绩和考试状态和上交时间
        String status = "考试结束";
        Date postTime = new Date();
        String score = "正确："+Tcount+"错误："+Fcount;
        String UserName = (String)session.getAttribute("loginUser");
        Integer sId = studentService.findIdByUserName(UserName);
        examService.updateExam(status,score,postTime,sId);

        session.removeAttribute("entryExam");//离开考试页面后，把人脸识别成功的标识去掉
            return new JsonUtil().serialize(ti);
    }
}
