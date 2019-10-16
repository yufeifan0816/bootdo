package com.bootdo.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class SmallBigImage {
 
  public static final void overlapImage(String bigPath, String smallPath) {
    try {
      new BufferedImage(1080, 1920, BufferedImage.TYPE_INT_RGB);
      BufferedImage big = ImageIO.read(new File(bigPath));
      BufferedImage small = ImageIO.read(new File(smallPath));
      Graphics2D g = big.createGraphics();
      int x = (big.getWidth() - small.getWidth()) ;
      int y = (big.getHeight() - small.getHeight()) ;
      g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
      g.dispose();
      ImageIO.write(big, "jpg", new File("F:\\temp\\111国旗01.jpg"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  /** the main method */
  public static final void main(String[] args) {
    overlapImage("F:\\temp\\头像.jpg", "F:\\temp\\国旗02.png");
  }
}