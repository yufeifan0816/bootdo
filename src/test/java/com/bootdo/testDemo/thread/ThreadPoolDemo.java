package com.bootdo.testDemo.thread;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-26 23:18
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            MyCallable myCallable = new MyCallable();
            myCallable.setValue("value"+i);
            Future<String> submit = executorService.submit(myCallable);
            futures.add(submit);
        }
        System.out.println("所有子线程开始执行....");
        for (Future<String> future : futures) {
            String s = future.get();
            System.out.println(s);
        }
        System.out.println("所有子线程执行结束....");
    }
}
