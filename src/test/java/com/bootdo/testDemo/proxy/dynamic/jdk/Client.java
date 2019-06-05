package com.bootdo.testDemo.proxy.dynamic.jdk;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:17
 **/
public class Client {
    public static void main(String[] args) {
        ProxyJdkSubject.getInstance(new RealJdkSubject()).doSomething();
    }
}
