package com.jlu.jtee.controller;

import com.jlu.jtee.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    StudentService studentService;


    @GetMapping("/java")
    public String toJavaPage(){
        return "java";
    }

    @GetMapping("/java/Exm")
    public String toJavaExm(){
        return "javaExm";
    }

    @GetMapping("/admin")
    public String toAdmin(){
        return "admin";
    }

    @GetMapping("/import")
    public String toImport(){
        return "import";
    }

    @GetMapping("/MyExam")
    public String toMyExam(){
        return "MyExam";
    }


    @GetMapping("/main")
    public String toMain(){
        return "home";
    }

    @GetMapping("/checkFace")
    @ResponseBody
    public int toCheckFace(HttpSession session ){
        session.setAttribute("check",1);
        //
        if (session.getAttribute("entryExam")!=null)
            return 1;//验证成功
        else return 0;

    }

    @GetMapping("/editFace")
    public String toEditFace(HttpSession session){
        session.removeAttribute("check");
        String username = (String)session.getAttribute("loginUser");
        String className = studentService.getClassName(username);
        if (className != null){
            session.setAttribute("className",className);
            session.setAttribute("Nameflag",1);
        }

        return "edit";
    }


    /**
     * 离开试卷列表页面就去掉 session的entryExam
     */
    @GetMapping("/outExam")
    @ResponseBody
    public String outExam(HttpSession session){
        session.removeAttribute("entryExam");
        return "退出考试界面";
    }
}
