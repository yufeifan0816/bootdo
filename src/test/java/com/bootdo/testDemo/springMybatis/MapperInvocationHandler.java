package com.bootdo.testDemo.springMybatis;

import org.apache.ibatis.annotations.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 15:08
 **/
public class MapperInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("queryAll")){
            Select annotation = method.getAnnotation(Select.class);
            String[] value = annotation.value();
            System.out.println(value[0]);
        }
        return null;
    }
}
