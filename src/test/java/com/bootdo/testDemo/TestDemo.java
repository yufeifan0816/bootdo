package com.bootdo.testDemo;

import com.bootdo.testDemo.biuld.CarBiuld;
import com.bootdo.weixin.utils.PicUtils;
import com.bootdo.weixin.utils.WeixinUtils;

public class TestDemo {
   private static int num =100;

   public static void main(String[] args) {
     /* CarBiuld carBiuld = new CarBiuld();
      carBiuld.setSequence("process2");
      carBiuld.setSequence("process4");
      carBiuld.setSequence("process3");
      carBiuld.getModel().run();*/
       PicUtils.DfAddWaterMark("yff"+ WeixinUtils.separator+"gt"+ WeixinUtils.separator+"2.png","yff"+WeixinUtils.separator+"gt"+ WeixinUtils.separator+"1111.png","尼玛还哦" );
   }
}
