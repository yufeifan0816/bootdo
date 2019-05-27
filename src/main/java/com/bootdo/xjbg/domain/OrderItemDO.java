package com.bootdo.xjbg.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 10:27:48
 */
public class OrderItemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//明细id
	private Long id;
	//工单id
	private Long orderId;
	//商品id
	private Long productId;
	//商品数量
	private Integer productAccount;
	//
	private Date createTime;
	//
	private String createUser;
	//
	private Date updateTime;
	//
	private String updateUser;

	/**
	 * 设置：明细id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：明细id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：工单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：工单id
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：商品id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * 设置：商品数量
	 */
	public void setProductAccount(Integer productAccount) {
		this.productAccount = productAccount;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getProductAccount() {
		return productAccount;
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
