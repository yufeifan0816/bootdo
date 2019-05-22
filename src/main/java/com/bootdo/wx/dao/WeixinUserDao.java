package com.bootdo.wx.dao;

import com.bootdo.wx.domain.WeixinUser;
import com.bootdo.wx.domain.WeixinUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yff
 * @email 1992lcg@163.com
 * @date 2019-05-22 17:51:52
 */
@Mapper
public interface WeixinUserDao {

	WeixinUserDO get(Long id);
	
	List<WeixinUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WeixinUserDO weixinUser);
	
	int update(WeixinUserDO weixinUser);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<WeixinUser> findByOpenId(String fromUserName);
}
