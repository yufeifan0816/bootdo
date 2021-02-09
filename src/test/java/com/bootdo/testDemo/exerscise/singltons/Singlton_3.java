package com.bootdo.testDemo.exerscise.singltons;

/**
 * Double CheckLock实现单例(懒汉)
 */
public class Singlton_3 {
    private static Singlton_3 singlton ;
    public static Singlton_3 getInstance(){
        if (singlton==null){
            synchronized (Singlton_3.class){
                if(singlton==null){
                    singlton = new Singlton_3();
                }
            }
        }
        return singlton;
    }

}
