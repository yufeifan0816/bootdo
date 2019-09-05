package com.bootdo.testDemo.collection;

import java.util.TreeSet;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-05 10:07
 **/
public class SetDemo {
    //①无序,不可重复
    public static void main(String[] args) {
        //基于treeMap,将值做位key存储在treeMap中,(TreeSet核心在于排序,默认自然排序,掉用值得compelto方法排序)
        TreeSet<Object> objects = new TreeSet<>();
        objects.add("111");
        objects.add("333");
        objects.add("222");
        System.out.println(objects.toString());
    }
}
