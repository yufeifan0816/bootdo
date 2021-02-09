package com.bootdo.testDemo.exerscise.singltons;

/**
 * 懒汉模式
 */
public class Singlton_2 {
    private static Singlton_2 singlton = null;
    public synchronized static Singlton_2 getInstance(){
            if(singlton==null){
                singlton = new Singlton_2();
            }
        return singlton;
    }

}
