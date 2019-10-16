package com.bootdo.testDemo.thread.Seckill;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-12 09:30
 **/
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            BuyTicket buyTicket = new BuyTicket("小爱" + i);
            Thread thread = new Thread(buyTicket);
            thread.start();
        }
    }
}
