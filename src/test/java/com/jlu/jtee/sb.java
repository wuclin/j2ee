package com.jlu.jtee;

import org.apache.commons.io.FileUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;

public class sb{
    //自己写一个把

    byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\face\\faceExam\\123\\Java集合类20210123013421\\1611380061199.jpg"));
    Mat mat = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);


    public sb() throws IOException {
    }
}