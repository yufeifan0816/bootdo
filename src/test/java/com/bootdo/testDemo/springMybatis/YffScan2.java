package com.bootdo.testDemo.springMybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({YffDefinitionRegister.class})
public @interface YffScan2 {
    String[] value() default {};
}
