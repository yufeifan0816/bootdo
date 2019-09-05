package com.bootdo.testDemo.thread;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-28 14:59
 **/
public class ThreadJoinDemo extends Thread {
    public static void main(String[] args) {
        Thread a = new Thread(new MyRunnable(), "A");
        Thread b = new Thread(new MyRunnable(), "B");
        Thread c = new Thread(new MyRunnable(), "C");
        Thread d = new Thread(new MyRunnable(a), "D");
        Thread e = new Thread(new MyRunnable(), "E");
        Thread f = new Thread(new MyRunnable(), "F");

        a.start();
        System.out.println("开启其他线程=========");
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();

    }
}
