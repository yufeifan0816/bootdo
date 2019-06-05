package com.bootdo.testDemo.proxy.dynamic.cjlb;

import com.bootdo.testDemo.proxy.staticProxy.AbstractSubject;
import com.bootdo.testDemo.proxy.staticProxy.ProxySubject;
import com.bootdo.testDemo.proxy.staticProxy.realSubject;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:17
 **/
public class Client {
    public static void main(String[] args) {
        RealCJLBSubject proxy1 = ProxyCJLBSubject.getProxy(RealCJLBSubject.class);
        RealCJLBSubject proxy2 = ProxyCJLBSubject.getProxy(RealCJLBSubject.class);
        System.out.println("proxy1===="+proxy1.getClass()+"       proxy2======="+proxy2.getClass());
//        proxy2.doSomething();
        System.out.println(ProxyCJLBSubject.getProxy(RealCJLBSubject2.class).ppp());
    }
}
