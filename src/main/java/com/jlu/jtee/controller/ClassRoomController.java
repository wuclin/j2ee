package com.jlu.jtee.controller;

import com.jlu.jtee.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ClassRoomController {
    @Autowired
    ClassRoomService classRoomService;

    @ResponseBody
    @GetMapping("/getClassName")
    public List GetClassName(){
        List<String> ClassName = new ArrayList<>();
        ClassName = classRoomService.SelectClassRoomName();
        return ClassName;
    }

    @ResponseBody
    @GetMapping("/setClass")
    public void SetClass(HttpSession session, String ClassName){
        String UserName = (String)session.getAttribute("loginUser");
        classRoomService.setClassByName(UserName,ClassName);
    }
}
