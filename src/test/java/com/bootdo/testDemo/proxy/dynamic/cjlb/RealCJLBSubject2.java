package com.bootdo.testDemo.proxy.dynamic.cjlb;

/**
 * @program: bootdo
 * @description: 真实主体将角色
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:08
 **/
public class RealCJLBSubject2 {

    public void doSomething() {
        System.out.println("干开始不干正经事儿了<<<<");
    }
    public String  ppp() {
        System.out.println("干开始不干正经事儿了<<<<");
        return "干完收工";
    }
}