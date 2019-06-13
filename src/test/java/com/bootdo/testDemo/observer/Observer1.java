package com.bootdo.testDemo.observer;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-13 14:43
 **/
public class Observer1 implements Iobserver{
    private String name ;

    public Observer1(String name) {
        this.name = name;
    }
    @Override
    public void guancha(String context) {
        System.out.println(this.name+"正在观察:"+context);
    }

}
