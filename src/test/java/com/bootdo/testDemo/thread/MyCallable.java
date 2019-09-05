package com.bootdo.testDemo.thread;

import java.util.concurrent.Callable;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-26 22:35
 **/
public class MyCallable implements Callable<String> {
    String value  = "";
    @Override
    public String call() throws Exception {
        System.out.println("value="+value);
        for (int i = 0; i < 3; i++) {
            System.out.println("thread wait "+(i+1)+"ms");
            Thread.currentThread().sleep(1000);
        }
        return "return value = "+value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
