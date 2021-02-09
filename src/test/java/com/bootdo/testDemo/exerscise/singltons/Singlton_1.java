package com.bootdo.testDemo.exerscise.singltons;

/**
 * 饿汉模式
 */
public class Singlton_1 {
    private static final Singlton_1 singlton = new Singlton_1();
    public static Singlton_1 getInstance(){
        return singlton;
    }

    public static void main(String[] args) {
        System.out.println(Singlton_5.SINGLTON_5);
        System.out.println(Singlton_5.SINGLTON_5);
        System.out.println(Singlton_5.SINGLTON_5);
    }
}
