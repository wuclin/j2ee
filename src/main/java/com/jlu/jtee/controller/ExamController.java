package com.jlu.jtee.controller;

import com.jlu.jtee.domain.Exam;
import com.jlu.jtee.service.ExamService;
import com.jlu.jtee.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller()
@RequestMapping("/Exam")
public class ExamController {
    private final static Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamService examService;

    @Autowired
    StudentService studentService;

    @GetMapping("BreakoutExam")
    @ResponseBody
    public String BreakoutExam(HttpSession session){
        String status= "中途退出";
        String score = "中途退出";
        Date postTime = new Date();
        String UserName = (String)session.getAttribute("loginUser");
        Integer sId = studentService.findIdByUserName(UserName);
        examService.updateExam(status,score,postTime,sId);
        logger.info("退出考试");
        return "已退出考试";
    }

    @GetMapping("findAllMyExam")
    public String findAllMyExam(HttpSession session, Model model){
        String UserName = (String)session.getAttribute("loginUser");
        Integer sId = studentService.findIdByUserName(UserName);
        List<Exam> exams = examService.findAllMyExamBySId(sId);
        model.addAttribute("exams",exams);
        return "MyExam";
    }


    @GetMapping("/getExam")
    @ResponseBody
    public List GetExam(String username, HttpSession session){
        int sid = studentService.findIdByUserName(username);

            session.setAttribute("faceName", username);

        List<Exam> Exam = examService.getExam(sid);
        return Exam;
    }
}
