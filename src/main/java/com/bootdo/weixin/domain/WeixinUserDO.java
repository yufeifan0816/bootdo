package com.bootdo.weixin.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yff
 * @email 1992lcg@163.com
 * @date 2019-05-22 17:51:52
 */
public class WeixinUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//openid是公众号的普通用户的唯一标识。
	private String openId;
	//是否取消关注0取消/1未取消
	private Integer isRemoved;
	//
	private Date createTime;
	//取消关注的时间
	private Date removeTime;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：openid是公众号的普通用户的唯一标识。
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：openid是公众号的普通用户的唯一标识。
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：是否取消关注0取消/1未取消
	 */
	public void setIsRemoved(Integer isRemoved) {
		this.isRemoved = isRemoved;
	}
	/**
	 * 获取：是否取消关注0取消/1未取消
	 */
	public Integer getIsRemoved() {
		return isRemoved;
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
	 * 设置：取消关注的时间
	 */
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}
	/**
	 * 获取：取消关注的时间
	 */
	public Date getRemoveTime() {
		return removeTime;
	}
}
