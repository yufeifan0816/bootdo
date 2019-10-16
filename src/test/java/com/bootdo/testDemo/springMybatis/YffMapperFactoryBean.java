package com.bootdo.testDemo.springMybatis;

import com.bootdo.testDemo.springMybatis.Mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 15:44
 **/
public class YffMapperFactoryBean implements FactoryBean {
    Class inteface ;

    public YffMapperFactoryBean(Class inteface) {
        this.inteface = inteface;
    }

    @Override
    public Object getObject() throws Exception {
        Object mapper = YffSqlSession.getMapper(inteface);
        return mapper;
    }

    @Override
    public Class<?> getObjectType() {
        return inteface;
    }
}
