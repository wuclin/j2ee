package com.jlu.jtee;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CameraClose extends JFrame {

    public static void main(String[] args) throws InterruptedException {
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
        window.pack();
        window.setVisible(true);
        window.setSize(300, 300);
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        Thread.sleep(5000);
        webcam.close();
        window.dispose();





    }
    void shutdown(){

    }
}
