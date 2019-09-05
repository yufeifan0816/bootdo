package com.bootdo.testDemo.springMybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-03 15:31
 **/
public class YffDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(YffScan.class.getName()));
        AnnotationAttributes annoAttrs2 = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName()));
//        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(YffScan2.class.getName()));
        String[] values = annoAttrs.getStringArray("value");
        Set<Class> scan = ClassScaner.scan(values, null);
        scan.forEach((v)->{
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(YffMapperFactoryBean.class);
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(v.getName());
            registry.registerBeanDefinition(v.getName(),beanDefinition);
        });
//        for (String pkg : annoAttrs.getStringArray("value")) {
//            if (StringUtils.hasText(pkg)) {
//                System.out.println(pkg);
//            }
//        }

    }
}
