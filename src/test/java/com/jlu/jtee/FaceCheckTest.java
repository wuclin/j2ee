package com.jlu.jtee;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.Arrays;

import static org.opencv.videoio.Videoio.CAP_DSHOW;

public class FaceCheckTest {
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

        //  比对本地2张图的人脸相似度 （越接近1越相似）
        String basePicPath = "D:\\test\\capture\\";

        double compareHist = compare_image("D:\\test\\capture\\login.jpg" , basePicPath + "1611288232156.jpg");
        System.out.println(compareHist);
        if (compareHist > 0.72) {
            System.out.println("人脸匹配");
        } else {
            System.out.println("人脸不匹配");
        }/**/
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
