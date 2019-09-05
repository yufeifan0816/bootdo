package com.bootdo.testDemo.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-26 22:34
 **/
public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<String> stringFutureTask = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        try {
                String s = stringFutureTask.get();
                System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
