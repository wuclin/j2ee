package com.jlu.jtee.controller;

import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/import")
public class QuestionImportController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionImportController.class);


    @Autowired
    ChoiceQuestionService choiceQuestionService;

    @GetMapping("choiceQuestion")
    public String importChoice(HttpSession session, Map<String,Object> map){

        File file = (File) session.getAttribute("fileName");
        String contributor = (String) session.getAttribute("loginUser");

        logger.info("导入选择题");
        if (file != null){
            int cnt = 0;//题目数
            String content = "";//题目
            String answer = "";//答案
            String type = "";//题目类型
            String[] choices = new String[6];//选项
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";
                ChoiceQuestion q = new ChoiceQuestion();
                while((line = br.readLine()) != null){
                    line = line.replace(" ", "");//将UTF-8的特殊空格替换掉，编码c2a0
                    line = line.replace((char) 65279, ' ');//对有BOM头的第一行做处理
                    line = line.trim();//去掉每一行的两端空格
                    if (line.equals(""))
                        continue;
                    else if (line.startsWith("######")){
                        continue;
                    }else if (line.startsWith("***")){
                        type = line.substring(3).trim();
                        q.setType(type);
                    }else if(line.startsWith("@@")){
                        int index = line.lastIndexOf(",");
                        content = line.substring(index+1,line.length()).trim();
                        q.setContent(content);
                    }else if(line.startsWith("-")){
                        char firstChar = line.charAt(1);
                        if (Character.isUpperCase(firstChar)){
                            switch (firstChar){
                                case 'A':
                                    choices[0] = line.substring(1,line.length()).trim();
                                    q.setChoiceA(choices[0]);
                                    break;
                                case 'B':
                                    choices[1] = line.substring(1,line.length()).trim();
                                    q.setChoiceB(choices[1]);
                                    break;
                                case 'C':
                                    choices[2] = line.substring(1,line.length()).trim();
                                    q.setChoiceC(choices[2]);
                                    break;
                                case 'D':
                                    choices[3] = line.substring(1,line.length()).trim();
                                    q.setChoiceD(choices[3]);
                                    break;
                                case 'E':
                                    choices[4] = line.substring(1,line.length()).trim();
                                    q.setChoiceE(choices[4]);
                                    break;
                                case 'F':
                                    choices[5] = line.substring(1,line.length()).trim();
                                    q.setChoiceF(choices[5]);
                                    break;
                            }
                        }
                    }else if (line.startsWith(">>>")){
                        answer = line.substring(3,line.length());
                        q.setAnswer(answer);
                    }else if (line.startsWith("%")){
                        cnt++;
                        q.setContributor(contributor);
                        q.setCreateTime(new Date());
                        choiceQuestionService.importChoice(q);
                        q = new ChoiceQuestion();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            logger.info("导入"+cnt+"道题目");
        }else{
            logger.info("文件不存在");
            map.put("Msg","导入失败");

            return "redirect:/import.html";
        }
        map.put("Msg","导入成功");
        return "redirect:/import.html";

    }


    /**
     * 解析文件 将文件中的信息分析后存进数据库
     *
     */
    @GetMapping("choicesQuestion")
    @ResponseBody
    public String choiceQuestionImport(ChoiceQuestion choiceQuestion, HttpSession session){
        File file = (File) session.getAttribute("filename");
        file.toString();
        logger.info("导入选择题");
        choiceQuestion.setCreateTime(new Date());
        choiceQuestionService.importChoice(choiceQuestion);
        return "全部加入成功";
    }

}
