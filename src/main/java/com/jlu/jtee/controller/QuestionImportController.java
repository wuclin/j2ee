package com.jlu.jtee.controller;

import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@RequestMapping("/import")
public class QuestionImportController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionImportController.class);


    @Autowired
    ChoiceQuestionService choiceQuestionService;

    @GetMapping("choiceQuestion")
    @ResponseBody
    public String importChoice(ChoiceQuestion choiceQuestion){
        choiceQuestion.setContent("1,下列哪些方法是ArrayList和LinkedList集合中都定义的（多选）（     ）");
        choiceQuestion.setChoiceA("A. add(Object o)");
        choiceQuestion.setChoiceB("B. removeFirst()");
        choiceQuestion.setChoiceC("C. remove(Object o)");
        choiceQuestion.setChoiceD("D. add(int index,Object o)");
        choiceQuestion.setAnswer("A,C,D");
        choiceQuestion.setContributor("测试");
        choiceQuestion.setCreateTime(new Date());
        choiceQuestion.setType("Java集合类");
        choiceQuestionService.importChoice(choiceQuestion);
        return "加入成功";

    }

    /**
     * 解析文件 将文件中的信息分析后存进数据库
     *
     */
    @GetMapping("choicesQuestion")
    @ResponseBody
    public String choiceQuestionImport(ChoiceQuestion choiceQuestion){
        logger.info("导入选择题");
        choiceQuestion.setCreateTime(new Date());
        choiceQuestionService.importChoice(choiceQuestion);
        return "全部加入成功";
    }

}
