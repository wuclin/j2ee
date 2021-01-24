package com.jlu.jtee.controller;

import com.jlu.jtee.service.StudentService;
import com.jlu.jtee.util.Base64Util;
import com.jlu.jtee.util.Camera;
import com.jlu.jtee.util.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.jlu.jtee.util.FaceUtil.compare_image;

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

    /*修改资料或注册是保存的图片原本
    * */
    @PostMapping("getPhoto")
    @ResponseBody
    public int GetPhoto(HttpServletRequest request,HttpSession session) {
        String username = "";
        int message = 00;
        String type = "faceOrigin";
        if (session.getAttribute("signUpUN") != null)//注册时使用
            username = (String)session.getAttribute("signUpUN");
        if (session.getAttribute("loginUser") != null)//登录时使用
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
        if (faceUtil.face(type,username)){
            message = 1;
            studentService.updateFaceId("D:\\face\\faceOrigin\\"+username+"\\"+username+"1.png",studentService.findIdByUserName(username));
        }
        else
            message = 2;

        return message;
    }

    /**
     * 加入考试时，进行需要进行人脸识别，判断是否是本人
     * @return
     */
    @PostMapping("getFace")
    @ResponseBody
    public int GetFace(HttpServletRequest request,HttpSession session){
        String username = "";
        int message = 11;
        String type = "faceCheck";
        if (session.getAttribute("loginUser") != null)//登录时使用
            username = (String)session.getAttribute("loginUser");

        String url = request.getParameter("imageData");//服务器拿到的base64带有前缀
        String Purl = url.substring(url.indexOf(",")+1);//去掉前缀
        Base64Util base64Util = new Base64Util();

        //如果存放原本的文件夹不存在则直接返回 提示用户先去录入照片
        File file1 = new File("D:\\face\\faceOrigin\\"+username);
        if  (!file1 .exists()  && !file1 .isDirectory()){
            message = 0;//"请先前往修改资料处拍摄照片";
            return message;
        }
        //保存拍摄的照片到本地
        //新建文件夹
        File file = new File("D:\\face\\faceCheck\\"+username);
        if  (!file .exists()  && !file .isDirectory()){
            file .mkdir();
        }

        base64Util.generateImage(Purl,"D:\\face\\faceCheck\\"+username+"\\"+username+"1.png");
        FaceUtil faceUtil = new FaceUtil();
        if (faceUtil.face(type,username)) {
            //把要对比图片的地址传进去
            double like = faceUtil.compare_image("D:\\face\\faceOrigin\\"+username+"\\"+username+"1.png","D:\\face\\faceCheck\\"+username+"\\"+username+"1.png");
            System.out.println(like);
            if (like > 0.72) {
                message = 2;//"人脸匹配";
                session.setAttribute("entryExam",1);//匹配成功，保存识别成功标识
            } else {
                message = 3;//"人脸不匹配";
            }
        }else {

            message = 1;//提示用户识别不到人像
        }
        return message;
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

    //要把用户名传进去
    @ResponseBody
    @GetMapping("UseCamera")
    public void useCamera(HttpSession session) throws InterruptedException, IOException {
        String username = "", ExamType = "";
        username = (String)session.getAttribute("loginUser");
        ExamType = (String)session.getAttribute("ExamType");
        Camera camera = new Camera(username,ExamType);

      //  return 1;
    }
}
