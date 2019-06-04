package com.bootdo.testDemo;

import com.bootdo.testDemo.biuld.CarBiuld;

public class TestDemo {
   private static int num =100;

   public static void main(String[] args) {
      CarBiuld carBiuld = new CarBiuld();
      carBiuld.setSequence("process2");
      carBiuld.setSequence("process4");
      carBiuld.setSequence("process3");
      carBiuld.getModel().run();
   }
}
