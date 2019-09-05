package com.bootdo.testDemo.thread;

/**
 * @program: bootdo
 * @description: 验证sleep 和wait 的区别（sleep（） 和wait（）都会释放cup资源，sleep不会改变锁行为， wait（）会释放锁）
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-29 09:52
 **/
public class SleepAndWaitDemo {
   final static  Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A  wait get lock");
                 synchronized (lock){
                     try {
                         System.out.println("Thread A sleep 200");
                         Thread.sleep(2000);//模拟程序执行时间
                         System.out.println("Thread A wait 1000 ");
                         lock.wait(1000);

                         System.out.println("Thread A is done ");
                     } catch (InterruptedException e) {
                         e.printStackTrace();

                     }

                 }
            }
        }).start();
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B  wait get lock");
                synchronized (lock){
                    try {
                        System.out.println("Thread B sleep 200");
                        Thread.sleep(10) ;
                        System.out.println("Thread B is done ");
//                        System.out.println("Thread B wait 1000 ");
//                        this.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }

                }
            }
        }).start();
    }
}
