package com.bootdo.testDemo.thread;

/**
 * @program: bootdo
 * @description: join表示等待该线程执行完后再执行
 * 例如:D线程中调用 A.join(),则表示等待A线程执行完成后再执行D 剩下的代码
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-29 11:11
 **/
public class MyRunnable implements Runnable {
    private Thread proveThread = null;
    public  MyRunnable(){}
    public MyRunnable(Thread thread){
        proveThread = thread;
    }
    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            System.out.println("线程"+name+"开启。。。。。。。");
            Thread.sleep(200);
            if(proveThread!=null){
                proveThread.join();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("线程"+name+"开始执行"+(i+1)+"=====");
                Thread.sleep(200);
            }
            System.out.println("线程"+name+"执行完了");
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }
}
