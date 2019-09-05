package com.bootdo.testDemo.springMybatis;

import java.lang.reflect.Proxy;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 15:05
 **/
public class YffSqlSession {
    public static Object getMapper(Class<?> clazz){
        Class<?>[] classes = {clazz};
        Object o =Proxy.newProxyInstance(clazz.getClassLoader(), classes, new MapperInvocationHandler());
        return  o;
   }
}
