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
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//工单id
	private Long id;
	//房间id
	private Long roomId;
	//工单类型
	private String orderType;
	//
	private Integer price;
	//已付款
	private Integer paidUp;
	//工单状态
	private String orderState;
	//
	private Date createTime;
	//
	private String createUser;
	//
	private Date updateTime;
	//
	private String updateUser;

	/**
	 * 设置：工单id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：工单id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：房间id
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * 获取：房间id
	 */
	public Long getRoomId() {
		return roomId;
	}
	/**
	 * 设置：工单类型
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：工单类型
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：已付款
	 */
	public void setPaidUp(Integer paidUp) {
		this.paidUp = paidUp;
	}
	/**
	 * 获取：已付款
	 */
	public Integer getPaidUp() {
		return paidUp;
	}
	/**
	 * 设置：工单状态
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：工单状态
	 */
	public String getOrderState() {
		return orderState;
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
