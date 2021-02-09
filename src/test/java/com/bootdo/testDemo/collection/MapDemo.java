package com.bootdo.testDemo.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-05 10:40
 **/
public class MapDemo {
    public static void main(String[] args) {
        //默认16
        //key==>Set(去重)
        //首次使用的时候才初始化

        HashMap<Object, Object> hashMpa = new HashMap<>();
        ConcurrentHashMap<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<String, String>();
        /***
         * put()的执行过程
         * ①判断hashMap是否被初始化,如果没有则初始化HashMap
         * ②hash(key)==求下标值.
         * ③如果下标值没有在hashMap中存在,则直接放入桶中
         * ④如果下标值存在了,则以链表的形式链家到其他值后面
         * ⑤如果链表的长度超过了阈值TREEIFY_THRESHOLD(默认8),则将链表转换为红黑数
         * ⑥如果长度小于UNTREEIFY_THRESHOLD(默认6),就把红黑树转换成链表
         * ⑦如果节点存在相同的key,就替换改值
         * ⑧如果桶满了(默认16*加载因子0.75),就调用resize()扩容,扩容2倍后重排
         **/
        hashMpa.put("1","aaa");

    }

  
}
