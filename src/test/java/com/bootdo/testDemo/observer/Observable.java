package com.bootdo.testDemo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-13 14:49
 **/
public class Observable implements Iobservable {
    private String name;
    private List<Iobserver> observers = new ArrayList<Iobserver>();

    public List<Iobserver> getObservers() {
        return observers;
    }
    public void addObserver(Iobserver observer){
        this.observers.add(observer);
    }

    public Observable(String name) {
        this.name = name;
    }

    @Override
    public void doSomething1() {
        System.out.println("我是"+name+"===我正在玩游戏");
        guancha(this.name+"正在打游戏");
    }

    @Override
    public void doSomething2() {
        System.out.println("我是"+name+"===我正在睡觉");
        guancha(this.name+"正在睡觉");
    }

    private void guancha(String s) {
        for (Iobserver observer : observers) {
            observer.guancha(s);
        }
    }
}
