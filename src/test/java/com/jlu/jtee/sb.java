package com.jlu.jtee;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class sb{
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource r = resourcePatternResolver.getResource("file:D:\\face\\faceExam\\123\\Java集合类20210124055127");
        System.out.println();
        String[] strArray = org.apache.commons.io.IOUtils.toString(r.getInputStream()).split("\\n");
        List list= Arrays.asList(strArray);
        System.out.println(list.size());
      //  String s = "Java基础2020-06-13 13:01:59";
      //  System.out.println(s.replaceAll("-|:| ", ""));;

        //to  ->  Java集合类20210124055127
    }
    //自己写一个把
    /*
    @RequestMapping("unit/bill/showeinvoice")
    @ResponseBody
    public void showEInvoice(HttpServletRequest request, HttpServletResponse response){
        FileInputStream fis = null;
        OutputStream os = null;
        String filepath = "D:\\face\\faceExam\\123\\Java集合类20210124055127";     //path是你服务器上图片的绝对路径
        File file = new File(filepath);
        if(file.exists()){
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
    */


}