package com.jlu.jtee;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        /*
        Date e = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddss");
        String a = sdf.format(e);
        System.out.println(a);

        String c = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        System.out.println(c);
        */
        //先创建用户名文件，再创建考试文件夹
        String file = "D:\\face\\faceExam\\"+ "123" ;
        String file2 = "D:\\face\\faceExam\\"+ "123" + "\\" + "javav" ;
        File a = new File(file2);
        if (!a.exists())
            a.mkdirs();
        else System.out.println("已存在");


    }
}
