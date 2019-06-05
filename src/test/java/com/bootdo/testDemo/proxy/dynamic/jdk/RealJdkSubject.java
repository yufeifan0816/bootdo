package com.bootdo.testDemo.proxy.dynamic.jdk;

/**
 * @program: bootdo
 * @description: 真实主体将角色
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:08
 **/
public class RealJdkSubject implements AbstractJdkSubject {

    @Override
    public void doSomething() {
        System.out.println("干开始干正经事儿了!!!");
    }
}
