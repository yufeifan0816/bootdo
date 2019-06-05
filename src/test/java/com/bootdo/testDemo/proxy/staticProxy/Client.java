package com.bootdo.testDemo.proxy.staticProxy;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:17
 **/
public class Client {
    public static void main(String[] args) {
        AbstractSubject Subject = new ProxySubject(new realSubject());
        Subject.doSomething();
    }
}
