package com.bootdo.xjbg.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 14:45:21
 */
public class ProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//商品名称
	private String productName;
	//进价
	private BigDecimal purchasePrice;
	//售价
	private BigDecimal sellingPrice;
	//商品图片地址
	private String productPic;
	//
	private Date createTime;
	//
	private String createUser;
	//
	private Date updateTime;
	//
	private String updateUser;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：进价
	 */
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * 获取：进价
	 */
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * 设置：售价
	 */
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	/**
	 * 获取：售价
	 */
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	/**
	 * 设置：商品图片地址
	 */
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	/**
	 * 获取：商品图片地址
	 */
	public String getProductPic() {
		return productPic;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public String getUpdateUser() {
		return updateUser;
	}
}
