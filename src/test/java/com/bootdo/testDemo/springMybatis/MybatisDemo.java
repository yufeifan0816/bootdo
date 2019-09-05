package com.bootdo.testDemo.springMybatis;

import com.alibaba.fastjson.JSON;
import com.bootdo.system.service.UserService;
import com.bootdo.testDemo.springMybatis.Mapper.OrderMapper;
import com.bootdo.testDemo.springMybatis.Mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 13:51
 **/

public class MybatisDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisConfig.class);
//        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
//        System.out.println(sqlSessionFactoryBean.getClass());
//        UserMapper bean = applicationContext.getBean(UserMapper.class);
//        System.out.println(JSON.toJSONString(bean.queryAll()));

//        YffSqlSession yffSqlSession = new YffSqlSession();
//        UserMapper mapper = yffSqlSession.getMapper(UserMapper.class);
//        List<Map<String, Object>> maps = mapper.queryAll();
//        System.out.println(JSON.toJSONString(maps));
        OrderMapper bean = applicationContext.getBean(OrderMapper.class);
        UserMapper bean2 = applicationContext.getBean(UserMapper.class);
        bean2.queryAll();
        List<Map<String, Object>> maps = bean.queryAll();
        System.out.println(JSON.toJSONString(maps));
    }

}
