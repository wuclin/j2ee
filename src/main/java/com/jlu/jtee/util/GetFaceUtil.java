package com.jlu.jtee.util;

import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.*;

public class GetFaceUtil {
    static CascadeClassifier faceDetector;

    static int i=0;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        faceDetector = new CascadeClassifier("D:\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
    }

    public static void main(String[] args) {


        String test1 = "D:\\test\\capture\\3.jpg";
        String test2 = "D:\\test\\capture\\4.jpg";
        String test3 = "D:\\test\\capture\\5.jpg";
        String test4 = "D:\\test\\capture\\6.jpg";
        String test5 = "D:\\test\\capture\\7.jpg";

       // face(test1);face(test2);face(test3);face(test4);face(test5);
    }


    /*
     * 调用这个查看是否为人像 保存作为主体
     * */
    public static boolean face(String fileName) throws IOException {
        int i = 0;
        // 1 读取OpenCV自带的人脸识别特征XML文件
        //OpenCV 图像识别库一般位于 opencv\sources\data 下面
        CascadeClassifier facebook=new CascadeClassifier("D:\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalcatface.xml");
        // 2 读取测试图片,中文路径要修改
        String imgPath = fileName;
       // Mat image= Imgcodecs.imread(imgPath);
        byte[] bytes = FileUtils.readFileToByteArray(new File(fileName));
        Mat image = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.IMREAD_UNCHANGED);
        if(image.empty()){
            System.out.println("image 内容不存在！");
            return false;
        }
        // 3 特征匹配
        MatOfRect face = new MatOfRect();
        faceDetector.detectMultiScale(image, face);
        // 4 匹配 Rect 矩阵 数组
        Rect[] rects=face.toArray();
        System.out.println("匹配到 "+rects.length+" 个人脸");
        // 5 为每张识别到的人脸画一个圈
        i =1 ;
        for (Rect rect : face.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 3);
            i++;
        }
        SaveMatToJpg(fileName, image);

       // Imgcodecs.imwrite(fileName, image);//把识别为人像的图片写到固定路径
        // 6 展示图片
        //  HighGui.imshow("人脸识别", image);
        //   HighGui.waitKey(0);

        if (rects.length>0)//是否识别到人脸
            return true;
        else
            return false;
    }

    /* 存储中文路径转化
    * */
    public static boolean SaveMatToJpg(String imgPath,Mat dstImage)  {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg",dstImage,mob);
        byte[] imageByte = mob.toArray();
        return writeFileByBytes(imgPath,imageByte,false);
    }
    public static boolean writeFileByBytes(String fileName, byte[] bytes, boolean append)  {
        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, append))){
            out.write(bytes);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
