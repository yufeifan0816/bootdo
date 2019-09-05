package com.bootdo.testDemo.springMybatis;

import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({YffDefinitionRegister.class})
public @interface YffScan {
    String[] value() default {};
}
