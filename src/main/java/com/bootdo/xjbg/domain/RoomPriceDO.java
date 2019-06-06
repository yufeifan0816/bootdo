package com.bootdo.xjbg.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-06-06 16:47:41
 */
public class RoomPriceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//房间id
	private Long roomId;
	//房间
	private RoomDO room;
	//入住类型
	private String orderType;
	//价格
	private Integer price;
	//
	private String createUser;
	//
	private Date createTime;
	//
	private String updateUser;
	//
	private Date updateTime;

	public RoomDO getRoom() {
		return room;
	}

	public void setRoom(RoomDO room) {
		this.room = room;
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
	 * 设置：入住类型
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：入住类型
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Integer getPrice() {
		return price;
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
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public String getUpdateUser() {
		return updateUser;
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
}
