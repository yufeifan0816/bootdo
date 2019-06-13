package com.bootdo.testDemo.observer;

/**
 * @program: bootdo
 * @description: 观察者模式
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-13 14:29
 **/
public class Client {
    public static void main(String[] args) {
        //被观察者
        Observable zhangsan = new Observable("张三");
        //观察者
        Observer1 lisi = new Observer1("李四");
        Observer1 wangwu = new Observer1("王五");
        //添加观察者
        zhangsan.addObserver(lisi);
        zhangsan.addObserver(wangwu);

        //被观察这开始doSomething
        zhangsan.doSomething1();
        zhangsan.doSomething2();

    }
}
