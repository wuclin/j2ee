package com.jlu.jtee.controller;

import com.jlu.jtee.domain.Student;
import com.jlu.jtee.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (studentService.checkoutUsername(username) != null
                && studentService.checkoutPassword(password) != null ){
            session.setAttribute("loginUser",username);
            session.setAttribute("flag",0);
            return "redirect:/main.html";
        }
        else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }

    @PostMapping("/admin")
    public String adminLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                             Map<String, Object> map,HttpSession session){
        if (studentService.checkoutAdmin(username,password) != null){
            session.setAttribute("loginUser","管理员:"+username);
            session.setAttribute("flag",1);
            return "redirect:/main.html";
        }
        else{
            map.put("msg","用户名或密码错误");
            return "admin";
        }
    }

    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/index.html";
    }
    @GetMapping("getStudent/{id}")
    public String GetStudent(@PathVariable int id){
        return studentService.sel(id).toString();
    }
}
