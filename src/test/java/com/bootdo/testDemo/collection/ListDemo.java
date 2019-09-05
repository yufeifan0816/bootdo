package com.bootdo.testDemo.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-05 09:35
 **/
public class ListDemo {
    //①有序(插入的顺序和),可重复,通过索引操作值
    public static void main(String[] args) {
        //ArrayList 基于数组, 查询效率相对较高,由于数组的非尾部增删操作会涉及到其他元素的移动,所以对修改删除的效率相对较低
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("aa");
        //LinkList 基于链表,查询效率相对较低,增删操作不会涉及到元素的移动,所以增删效率相对较高
        LinkedList linkedList = new LinkedList();
        //基于数组,内部所有对元素操作的公共public方法都被synchronized 修饰 属于线程安全的.
        Vector<Object> objects1 = new Vector<>();
    }
}
