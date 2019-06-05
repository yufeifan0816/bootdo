package com.bootdo.testDemo.proxy.dynamic.jdk;

import com.bootdo.testDemo.proxy.staticProxy.AbstractSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: bootdo
 * @description: 代理主体角色
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:10
 **/
public class ProxyJdkSubject implements InvocationHandler {
    private AbstractJdkSubject subject = null;

    public ProxyJdkSubject(AbstractJdkSubject subject) {
        this.subject = subject;
    }


    private void after() {
        System.out.println("事后一根烟");
    }

    private void before() {
        System.out.println("先做好安全措施");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("doSomething")) {
            System.out.println("===========这是真的jdk动态代理=====================");
            before();
            method.invoke(subject, args);
            after();
        }
        return null;
    }

    public static AbstractJdkSubject getInstance(AbstractJdkSubject subject) {
        ProxyJdkSubject proxyJdkSubject = new ProxyJdkSubject(subject);
        ClassLoader classLoader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        return (AbstractJdkSubject) Proxy.newProxyInstance(classLoader, interfaces, proxyJdkSubject);
    }
}
