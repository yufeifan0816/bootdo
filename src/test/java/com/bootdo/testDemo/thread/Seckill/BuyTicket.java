package com.bootdo.testDemo.thread.Seckill;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-12 09:22
 **/
public class BuyTicket implements Runnable {
    private static int ticket = 100;
    private static  int num = 0;
    private String name ;
    private static final Object obj = new Object();
    public BuyTicket(String name) {
        this.name = name;
    }
    /**悲观锁*/
//    @Override
//    public void run() {
//        synchronized (obj){
//            if(ticket<=0){
//                System.out.println("票已经卖完了,"+name+"没有买到票");
//                return;
//            }
//            //购买逻辑
//            System.out.println(name+"购到第"+(++num)+"张票");
//            ticket--;
//            System.out.println("剩余"+ticket+"张票");
//        }
//    }
    /**乐观锁**/
    @Override
    public void run() {
        //①获取去欠票之前的票数
        boolean tag = true;
        while(tag){
            int beforeNum = ticket;
            if(ticket<=0){
                System.out.println("票已经卖完了,"+name+"没有买到票");
                return;
            }
            //购买逻辑
            try {
                System.out.println(name+"正在购买第"+(++num)+"张票");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
            if(ticket==beforeNum){
                System.out.println(name+"购买成功+++++++++++++++++++++++++++++++++++++++++++");
                tag=false;
            }else{
                System.out.println("购买失败----------------------------");
                ticket++;
                num--;

            }
            System.out.println("剩余"+ticket+"张票");
        }

    }
}
