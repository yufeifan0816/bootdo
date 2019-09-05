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
//@YffScan2("com.bootdo.testDemo.springMybatis.Mapper")
//@MapperScan("com.bootdo.testDemo.springMybatis")
public class MybatisConfig {
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        return sqlSessionFactoryBean.getObject();
//    }
//    @Bean
//    public DataSource dataSource(){
//        DruidDataSource datasouce =  new DruidDataSource();
//        datasouce.setUsername("root");
//        datasouce.setPassword("root");
//        datasouce.setDriverClassName("com.mysql.jdbc.Driver");
//        datasouce.setUrl("jdbc:mysql://127.0.0.1:3306/bootdo?useUnicode=true&characterEncoding=utf8");
//        return datasouce;
//    }
//    @Bean
//    public UserMapper userMapper() throws Exception {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
//        return sqlSessionTemplate.getMapper(UserMapper.class);
//    }

}
