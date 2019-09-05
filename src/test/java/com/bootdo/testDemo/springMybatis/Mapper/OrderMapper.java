package com.bootdo.testDemo.springMybatis.Mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-04 10:36
 **/
public interface  OrderMapper {
    @Select("select * from sys_order")
    List<Map<String,Object>> queryAll();
}
