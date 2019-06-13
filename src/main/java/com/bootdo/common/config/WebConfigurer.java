package com.bootdo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.reflect.Field;

@Component
class WebConfigurer implements WebMvcConfigurer {
	@Autowired
	BootdoConfig bootdoConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().contains("win")){//windows 系统
			System.out.println("这是windows");
			registry.addResourceHandler(BootdoConfig.UPLOAD_PATH+"**").addResourceLocations("file:///"+"D:"+bootdoConfig.getUploadPath());
			registry.addResourceHandler(BootdoConfig.PRODUCT_PATH+"**").addResourceLocations("file:///"+"D:"+bootdoConfig.getProductPath());
		}else{
			System.out.println("这是linux");
			registry.addResourceHandler(BootdoConfig.UPLOAD_PATH+"**").addResourceLocations("http://39.108.251.77:8888/"+bootdoConfig.getUploadPath());
			registry.addResourceHandler(BootdoConfig.PRODUCT_PATH+"**").addResourceLocations("http://39.108.251.77:8888/"+bootdoConfig.getProductPath());
		}
	}

}