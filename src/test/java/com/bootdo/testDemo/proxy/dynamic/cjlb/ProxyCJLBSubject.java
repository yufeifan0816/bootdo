package com.bootdo.testDemo.proxy.dynamic.cjlb;

import com.bootdo.testDemo.proxy.dynamic.jdk.AbstractJdkSubject;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: bootdo
 * @description: 代理主体角色
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:10
 **/
public class ProxyCJLBSubject implements MethodInterceptor {


    private void after() {
        System.out.println("事后一根烟");
    }

    private void before() {
        System.out.println("先做好安全措施");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("===========真的cjlb动态代理============");
        before();
        Object o1 = methodProxy.invokeSuper(o, objects);
        after();
        return o1;
    }
    public static <T> T  getProxy(Class<T> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        ProxyCJLBSubject proxyCJLBSubject = new ProxyCJLBSubject();
        System.out.println(proxyCJLBSubject.toString());
        enhancer.setCallback(proxyCJLBSubject );
        T t = (T) enhancer.create();
        return t;
    }
}
