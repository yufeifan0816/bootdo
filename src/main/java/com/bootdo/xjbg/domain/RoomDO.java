package com.bootdo.xjbg.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-24 09:34:36
 */
public class RoomDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//房间编号
	private String roomNo;
	//房间类型
	private Integer roomType;
	//楼层
	private Integer floor;
	//房间状态
	private Integer roomState;
	//标价
	private Integer price;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private Long createUser;
	//
	private Long updateUser;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：房间编号
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	/**
	 * 获取：房间编号
	 */
	public String getRoomNo() {
		return roomNo;
	}
	/**
	 * 设置：房间类型
	 */
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	/**
	 * 获取：房间类型
	 */
	public Integer getRoomType() {
		return roomType;
	}
	/**
	 * 设置：楼层
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getFloor() {
		return floor;
	}
	/**
	 * 设置：房间状态
	 */
	public void setRoomState(Integer roomState) {
		this.roomState = roomState;
	}
	/**
	 * 获取：房间状态
	 */
	public Integer getRoomState() {
		return roomState;
	}
	/**
	 * 设置：标价
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：标价
	 */
	public Integer getPrice() {
		return price;
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
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
}
