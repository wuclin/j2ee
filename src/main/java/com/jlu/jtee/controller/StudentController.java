package com.jlu.jtee.controller;

import com.jlu.jtee.domain.Student;
import com.jlu.jtee.service.StudentService;
import com.jlu.jtee.util.Base64Util;
import com.jlu.jtee.util.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/Student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (studentService.checkoutUser(username,password) !=null){
            session.setAttribute("loginUser",username);
            session.setAttribute("flag",0);
            session.removeAttribute("signUpUN");
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

    @PostMapping("getPhoto")
    @ResponseBody
    public String GetPhoto(HttpServletRequest request,HttpSession session) {
        String username = "";
        String message = "";
        if (session.getAttribute("signUpUN") != null)//注册时使用
            username = (String)session.getAttribute("signUpUN");
        if (session.getAttribute("loginUser") != null)//注册时使用
            username = (String)session.getAttribute("loginUser");

        String url = request.getParameter("imageData");//服务器拿到的base64带有前缀
        String Purl = url.substring(url.indexOf(",")+1);//去掉前缀
        Base64Util base64Util = new Base64Util();

        //保存拍摄的照片到本地
        //新建文件夹
        File file = new File("D:\\face\\faceOrigin\\"+username);
        if  (!file .exists()  && !file .isDirectory()){
            file .mkdir();
        }

        base64Util.generateImage(Purl,"D:\\face\\faceOrigin\\"+username+"\\"+username+"1.png");
        //识别是否是人脸
        FaceUtil faceUtil = new FaceUtil();
        if (faceUtil.face(username)){
            message = "识别成功";
            studentService.updateFaceId("D:\\face\\faceOrigin\\"+username+"\\"+username+"1.png",studentService.findIdByUserName(username));
        }
        else
            message = "识别失败请拍照重试，或登录后重新拍照";

        return message;
    }

    @GetMapping("getFace")
    @ResponseBody
    public String GetFace(){
        FaceUtil faceUtil = new FaceUtil();
         faceUtil.getVideoFromCamera();

         return "photo";
    }

    @GetMapping("checkUserName")
    @ResponseBody
    public int checkUserName(String username){

        int message;
        if (studentService.checkoutUsername(username)!=null){
            message = 1;
        }else {
            message = 0;
        }
        return message;
    }

    @PostMapping("signUp")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,HttpSession session){
      try {
          studentService.insertStudent(username,password);
      }catch (Exception e)
      {
          e.printStackTrace();
      }
      //注册成功后把注册的用户名存在session中，如果想在注册的时候拍摄照片可以使用
      session.setAttribute("signUpUN",username);
        return "photo";

    }
}
