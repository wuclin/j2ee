package com.jlu.jtee;
/*
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;
import org.bytedeco.opencv.opencv_core.Mat;

public class JavaCVUtil {*/
/**
 * 使用1.1版本javacv
 * 后面版本的已修改
 * 这里需要javacpp mat
 * 另外一边需要core mat
 */

        /**
         *
         * 功能说明:读取图像
         * @param filePath
         * 文件路径,可以包含中文
         * @return Mat
         * @time:2016年3月31日下午1:26:51
         * @author:linghushaoxia
         * @exception:
         *
         **/
    /**
     * 转为mat
     * 1、由Java2D的image转为javacv自定义的Frame
     * 2、由Frame转为Mat
     * 用1.5的包时，convert方法变成返回core，但是需要的javacv
     */
    /**
     *
     * 功能说明:保存mat到指定路径
     * @param mat
     * 要保存的Mat
     * @param filePath
     * 保存路径
     * @time:2016年3月31日下午8:39:50
     * @author:linghushaoxia
     * @exception:
     *
     */    /**
     * 将mat转为java的BufferedImage
     * @return
     */
/*
        public static Mat imRead(String filePath){
           org.bytedeco.opencv.opencv_core.Mat mat = null;
            try {
                //使用java2D读取图像
                Image image = ImageIO.read(new File(filePath));

                Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
                Frame frame = java2dFrameConverter.convert((BufferedImage) image);
                ToMat converter = new OpenCVFrameConverter.ToMat();
                mat = converter.convert(frame);

            } catch (Exception e) {
                System.out.println("读取图像出现异常：filePath="+filePath);
                e.printStackTrace();
            }
            return mat;
        }


        public static boolean imWrite(Mat mat,String filePath){
            try {

                ToMat convert= new ToMat();
                Frame frame= convert.convert(mat);
                Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
                BufferedImage bufferedImage= java2dFrameConverter.convert(frame);
                ImageIO.write(bufferedImage, "PNG", new File(filePath));

                return true;
            } catch (Exception e) {
                System.out.println("保存文件出现异常:"+filePath);
                e.printStackTrace();
            }
            return false;
        }
    }
*/
