package com.bootdo.wx.service;

import com.bootdo.wx.domain.WeixinUserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yff
 * @email 1992lcg@163.com
 * @date 2019-05-22 17:51:52
 */
public interface WeixinUserService {
	
	WeixinUserDO get(Long id);
	
	List<WeixinUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WeixinUserDO weixinUser);
	
	int update(WeixinUserDO weixinUser);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<WeixinUserDO> findByOpenId(String fromUserName);
}
