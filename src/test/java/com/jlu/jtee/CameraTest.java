package com.jlu.jtee;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.jlu.jtee.util.FaceCheckUtil;
import com.jlu.jtee.util.GetFaceUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * 拍照的同时人脸检测,检测到之后和注册图片进行对比
 * 存在问题：歪头识别不到，精度不够
 * */
public class CameraTest extends JFrame {

    public CameraTest() throws InterruptedException, IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        JFrame window = new JFrame("摄像头");
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setSize(300, 300);
        window.setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                webcam.close();
                super.windowClosing(e);

            }

        });
        //固定时间截取照片
        while(true){
            String fileName = "D:\\test\\capture\\"+System.currentTimeMillis();
            WebcamUtils.capture(webcam, fileName);
            if (GetFaceUtil.face(fileName+".jpg")){
                double compareHist = FaceCheckUtil.compare_image(fileName+".jpg","D:\\test\\capture\\login.jpg");
                System.out.println(compareHist);
                if (compareHist > 0.72) {
                    System.out.println("人脸匹配");
                } else {
                    System.out.println("人脸不匹配");
                }
            }
            Thread.sleep(5000);
        }


    }

}


