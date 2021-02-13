package com.jlu.jtee.controller;

import com.jlu.jtee.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

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



    @RequestMapping("unit/bill/showeinvoice/{filename}")
    @ResponseBody
    public void showEInvoice(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              @PathVariable("filename") String filename,@RequestParam String f) {
        String faceName =(String) session.getAttribute("faceName");
        FileInputStream fis = null;
        OutputStream os = null;
      //  String filepath = "D:\\face\\faceExam\\1231\\"+ filename+"\\1.jpg";     //path是你服务器上图片的绝对路径
        String filepath = "D:\\face\\faceExam\\" + faceName + "\\" + filename + "\\" + f;
        System.out.println(filepath);
        File file = new File(filepath);
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/jpg");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @GetMapping("/toDetail")
    public String toDetail(){
        return "photoshow";
    }

}
