package com.bootdo.weixin.service.impl;

import com.bootdo.weixin.dao.WeixinUserDao;
import com.bootdo.weixin.domain.WeixinUserDO;
import com.bootdo.weixin.service.WeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



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
	public List<WeixinUserDO> findByOpenId(String fromUserName) {
		return weixinUserDao.findByOpenId(fromUserName);
	}

}
