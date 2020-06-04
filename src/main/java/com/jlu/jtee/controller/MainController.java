package com.jlu.jtee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
}
