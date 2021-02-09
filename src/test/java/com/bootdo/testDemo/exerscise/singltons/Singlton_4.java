package com.bootdo.testDemo.exerscise.singltons;

/**
 *静态内部类实现模式（线程安全，调用效率高，可以延时加载）
 */
public class Singlton_4 {
    private static class Singolton {
        private static Singlton_4 singlton4 = new Singlton_4();
    }
    public static Singlton_4 getInstance(){
        return Singolton.singlton4;
    }
}
