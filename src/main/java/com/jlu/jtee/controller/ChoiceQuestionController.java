package com.jlu.jtee.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.domain.Option;
import com.jlu.jtee.service.ChoiceQuestionService;
import com.jlu.jtee.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/choice")
public class ChoiceQuestionController {
    private final static Logger logger = LoggerFactory.getLogger(ChoiceQuestionController.class);


    @Autowired
    ChoiceQuestionService choiceQuestionService;

    @GetMapping("getTypelist")
    public String getTypeList(Model model){
        List<String> list = choiceQuestionService.getTypeList();
        model.addAttribute("TypeList",list);
        return "javaExm";
    }

    @GetMapping("getListByType")
    public String getListByType(@RequestParam("type")String type,Model model){
        List<ChoiceQuestion> list = choiceQuestionService.getListByType(type);
        model.addAttribute("Question",list);
        return "javaExmDetail";
    }

    @PostMapping("checkAnswer")
    @ResponseBody
    public String checkAnswer(HttpServletRequest request){
        ArrayList<String> daAn = new ArrayList<String>();
        ArrayList<Option> ti = new ArrayList<>();
        String ds = request.getParameter("postData");//字符串
        JSONArray jsonArray = JSONArray.parseArray(ds);
        for (int i=0;i<jsonArray.size();i++){
            String TiHao = jsonArray.getJSONObject(i).getString("TiHao");
            String answer = choiceQuestionService.getAnswerById(Integer.valueOf(TiHao));
            Option option = new Option(TiHao,answer);
            ti.add(option);
        }
        logger.info("查找答案");
        logger.info(ti.toString());


            return new JsonUtil().serialize(ti);
    }
}
