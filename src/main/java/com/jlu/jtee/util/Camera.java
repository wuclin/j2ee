package com.jlu.jtee.util;

import com.github.sarxos.webcam.*;
import com.jlu.jtee.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 用于考试时的照相机，
 * */
public class Camera {
        static  Webcam webcam;
        WebcamPanel panel;
        static  JFrame window;
    public Camera(String username,String ExamType,String ExamTime) throws InterruptedException, IOException {
        System.setProperty("java.awt.headless", "false");//要把这一句加在这里，不然会报错，加在run config没用
        //修改，把共用框架拿出
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        window = new JFrame("摄像头");
        window.add(panel);
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
        window.setSize(300, 300);
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                webcam.close();
                super.windowClosing(e);
            }
        });
        String originFileName = "D:\\face\\faceOrigin\\"+username+"\\"+username+"2.png";
        ExamType = ExamType + ExamTime ;

        //固定时间截取照片
        while(true & window.isVisible()){
            //这里想要先创建ExamType目录，username目录，即两级目录，所以要递归创建
            //必须先有文件夹才可以有文件
            String fileParent = "D:\\face\\faceExam\\"+username+"\\"+ExamType;
            if (!new File(fileParent).exists())
                new File(fileParent).mkdirs();
            String fileName = "D:\\face\\faceExam\\"+username+"\\"+ExamType+"\\"+ExamTime;
            WebcamUtils.capture(webcam, fileName);
            if (GetFaceUtil.face(fileName+".jpg")){
                double compareHist = FaceCheckUtil.compare_image(fileName+".jpg",originFileName);
                System.out.println(compareHist);
                if (compareHist > 0.72) {
                    System.out.println("人脸匹配");
                } else {
                    System.out.println("人脸不匹配");
                }
            }
            Thread.sleep(5000);
        }
    //终于
    }

    public static void shutDownCamera(){
        webcam.close();
        window.dispose();
    }
    }
