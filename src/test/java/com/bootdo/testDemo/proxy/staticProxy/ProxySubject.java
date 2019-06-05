package com.bootdo.testDemo.proxy.staticProxy;

/**
 * @program: bootdo
 * @description: 代理主体角色
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-05 09:10
 **/
public class ProxySubject implements AbstractSubject {
    private AbstractSubject subject = null;
    public ProxySubject(AbstractSubject subject){
        this.subject = subject;
    }

    @Override
    public void doSomething() {
        this.before();
        this.subject.doSomething();
        this.after();
    }

    private void after() {
        System.out.println("事后一根烟");
    }

    private void before() {
        System.out.println("先做好安全措施");
    }
}
