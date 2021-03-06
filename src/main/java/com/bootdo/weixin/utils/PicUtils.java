package com.bootdo.weixin.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-21 19:00
 **/
public class PicUtils {
    public static File fDir = new File(File.separator);
    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     */
    public static void DfAddWaterMark(String srcImgPath, String tarImgPath,String waterMarkContent){
        Font font = new Font("微软雅黑", Font.PLAIN, 35);                     //水印字体
        Color color=new Color(0,0,0);                               //水印图片色彩以及透明度
        addWaterMark(srcImgPath,tarImgPath,waterMarkContent,color,font);
    }
    public static void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(fDir,srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
//            int x = srcImgWidth - 3*getWatermarkLength(waterMarkContent, g);
//            int y = srcImgHeight - 3*getWatermarkLength(waterMarkContent, g);
            int x = srcImgWidth/2-getWatermarkLength(waterMarkContent, g)/2;
            int y = srcImgHeight-getWatermarkLength(waterMarkContent, g)/(waterMarkContent.length()*2);
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(new File(fDir,tarImgPath));
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
