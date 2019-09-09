package com.hyt.config;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @Author: 胡宜涛
 * @Date: 2019/9/7
 * @Version 1.0
 */
public class Base64Utils {


    /**
     * 将图片文件转换成base64字符串，参数为该图片的路径
     *
     * @param imageFile
     * @return java.lang.String
     */
    public String ImageToBase64(String imageFile) {
        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        if (data != null) {
            return "data:image/jpeg;base64," + encoder.encode(data);// 返回Base64编码过的字节数组字符串
        }
        return null;
    }

    /**
     * 将base64解码成图片并保存在传入的路径下
     * 第一个参数为base64 ，第二个参数为路径
     *
     * @param base64, imgFilePath
     * @return boolean
     */
    public boolean Base64ToImage(String base64, String imgFilePath) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 用来测试工具类是否成功
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        String path = "C:/Users/YD/Pictures/1.jpg";
        Base64Utils base64Utils = new Base64Utils();
        String s = base64Utils.ImageToBase64(path);
        System.out.println(s);
        String newpath = "C:/Users/YD/Pictures/asd.jpg";
        boolean b = base64Utils.Base64ToImage(s, newpath);
        System.out.println(b);
    }

}