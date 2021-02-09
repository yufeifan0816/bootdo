package com.bootdo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    public ServletInitializer() {
        System.out.println("初始化ServletInitializer");
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootdoApplication.class);//MyApplication是启动类名
    }
}
