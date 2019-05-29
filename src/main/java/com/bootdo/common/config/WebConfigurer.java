package com.bootdo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.reflect.Field;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
	@Autowired
	BootdoConfig bootdoConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().contains("win")){//windows 系统
			registry.addResourceHandler(BootdoConfig.UPLOAD_PATH+"**").addResourceLocations("file:///"+bootdoConfig.getUploadPath());
			registry.addResourceHandler(BootdoConfig.PRODUCT_PATH+"**").addResourceLocations("file:///"+bootdoConfig.getProductPath());
		}else{
			registry.addResourceHandler(BootdoConfig.UPLOAD_PATH+"**").addResourceLocations("file:/"+bootdoConfig.getUploadPath());
			registry.addResourceHandler(BootdoConfig.PRODUCT_PATH+"**").addResourceLocations("file:/"+bootdoConfig.getProductPath());
		}
	}

}