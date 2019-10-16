package com.bootdo.testDemo.springMybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 13:58
 **/
@ComponentScan("com.bootdo.testDemo.springMybatis")
@YffScan("com.bootdo.testDemo.springMybatis.Mapper")
//@MapperScan("com.bootdo.testDemo.springMybatis")
public class MybatisConfig {

}
