package com.bootdo.wx.service.impl;

import com.bootdo.wx.domain.WeixinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.wx.dao.WeixinUserDao;
import com.bootdo.wx.domain.WeixinUserDO;
import com.bootdo.wx.service.WeixinUserService;



@Service
public class WeixinUserServiceImpl implements WeixinUserService {
	@Autowired
	private WeixinUserDao weixinUserDao;
	
	@Override
	public WeixinUserDO get(Long id){
		return weixinUserDao.get(id);
	}
	
	@Override
	public List<WeixinUserDO> list(Map<String, Object> map){
		return weixinUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return weixinUserDao.count(map);
	}
	
	@Override
	public int save(WeixinUserDO weixinUser){
		return weixinUserDao.save(weixinUser);
	}
	
	@Override
	public int update(WeixinUserDO weixinUser){
		return weixinUserDao.update(weixinUser);
	}
	
	@Override
	public int remove(Long id){
		return weixinUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return weixinUserDao.batchRemove(ids);
	}

	@Override
	public List<WeixinUser> findByOpenId(String fromUserName) {
		return weixinUserDao.findByOpenId(fromUserName);
	}

}
