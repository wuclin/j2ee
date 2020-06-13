package com.jlu.jtee.util;


import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import java.util.Arrays;
import java.util.Random;

import static org.opencv.videoio.Videoio.CAP_DSHOW;

public class FaceUtil {

    static CascadeClassifier faceDetector;

    static int i=0;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        faceDetector = new CascadeClassifier("D:\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
    }

    public static void main(String[] args) {

        // 本地图片人脸识别，识别成功 返回对应信息
     //   face("test3");
    //    face("test4");
        /*
        //  比对本地2张图的人脸相似度 （越接近1越相似）
        String basePicPath = "D:\\face\\";

        double compareHist = compare_image(basePicPath + "536314915.png", basePicPath + "1369585767.png");
        System.out.println(compareHist);
        if (compareHist > 0.72) {
            System.out.println("人脸匹配");
        } else {
            System.out.println("人脸不匹配");
        }*/
    }

    public static void getVideoFromCamera() {
        //1 如果要从摄像头获取视频 则要在 VideoCapture 的构造方法写 0
        VideoCapture capture=new VideoCapture(0,CAP_DSHOW);
        Mat video=new Mat();
        int index=0;
        if (capture.isOpened()) {
            while(i<3) {// 匹配成功3次退出
                capture.read(video);
                HighGui.imshow("实时人脸识别", getFace(video));
                index=HighGui.waitKey(100);
                if (index==27) {
                    capture.release();
                    break;
                }
            }
        }else{
            System.out.println("摄像头未开启");
        }
        try {
            capture.release();
            Thread.sleep(10000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }

    public static Mat getFace(Mat image) {
        // 1 读取OpenCV自带的人脸识别特征XML文件(faceDetector)
//        CascadeClassifier facebook=new CascadeClassifier("D:\\Sofeware\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        // 2  特征匹配类
        MatOfRect face = new MatOfRect();
        // 3 特征匹配
        faceDetector.detectMultiScale(image, face);
        Rect[] rects=face.toArray();
        System.out.println("匹配到 "+rects.length+" 个人脸");
        if(rects != null && rects.length >= 1) {

            // 4 为每张识别到的人脸画一个圈
            for (int i = 0; i < rects.length; i++) {
                Imgproc.rectangle(image, new Point(rects[i].x, rects[i].y), new Point(rects[i].x + rects[i].width, rects[i].y + rects[i].height), new Scalar(0, 255, 0));
                Imgproc.putText(image, "Human", new Point(rects[i].x, rects[i].y), Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX, 1.0, new Scalar(0, 255, 0), 1, Imgproc.LINE_AA, false);
                //Mat dst=image.clone();
                //Imgproc.resize(image, image, new Size(300,300));
            }
            i++;
            if(i==3) {// 获取匹配成功第10次的照片
                Imgcodecs.imwrite("D:\\face\\" +"face1.png", image);
            }
        }
        return image;
    }
/*
* 调用这个查看是否为人像 保存作为主体
* */
    public static boolean face(String type,String fileName) {
        // 1 读取OpenCV自带的人脸识别特征XML文件
        //OpenCV 图像识别库一般位于 opencv\sources\data 下面
//        CascadeClassifier facebook=new CascadeClassifier("D:\\Sofeware\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        // 2 读取测试图片
        String imgPath = "D:\\face\\"+type+"\\"+fileName+"\\"+fileName+"1.png";
        Mat image=Imgcodecs.imread(imgPath);
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
        int i =1 ;
        for (Rect rect : face.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 3);
            i++;
        }
        Imgcodecs.imwrite("D:\\face\\"+type+"\\"+fileName+"\\"+fileName+"2.png", image);//把识别为人像的图片写到固定路径
        // 6 展示图片
      //  HighGui.imshow("人脸识别", image);
     //   HighGui.waitKey(0);

        if (rects.length>0)//是否识别到人脸
            return true;
        else
            return false;
    }
    /**
     * 人脸比对
     * @param img_1
     * @param img_2
     * @return
     */
    public static double compare_image(String img_1, String img_2) {
        Mat mat_1 = conv_Mat(img_1);
        Mat mat_2 = conv_Mat(img_2);



        Mat hist_1 = new Mat();
        Mat hist_2 = new Mat();

        //颜色范围
        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        //直方图大小， 越大匹配越精确 (越慢)
        MatOfInt histSize = new MatOfInt(1000);

        Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), new Mat(), hist_1, histSize, ranges);
        Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0), new Mat(), hist_2, histSize, ranges);

        // CORREL 相关系数
        double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
        return res;
    }

    private static Mat conv_Mat(String img_1) {
        Mat image0 = Imgcodecs.imread(img_1);

        Mat image = new Mat();
        //灰度转换
        Imgproc.cvtColor(image0, image, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faceDetections = new MatOfRect();
        //探测人脸
        faceDetector.detectMultiScale(image, faceDetections);

        // rect中是人脸图片的范围
        for (Rect rect : faceDetections.toArray()) {
            //切割rect人脸
            Mat mat = new Mat(image, rect);
            return mat;
        }
        return null;
    }
}
