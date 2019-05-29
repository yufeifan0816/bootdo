package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bootdo")
public class BootdoConfig {
	public final static String PRODUCT_PATH="/productPic/";
	public final static String UPLOAD_PATH="/files/";
	//上传路径
	private String uploadPath;
	//商品图片上传路径
	private String productPath;

	private String username;

	private String password;

	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
