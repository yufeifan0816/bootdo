package com.bootdo.xjbg.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.dao.RoomDao;
import com.bootdo.xjbg.domain.RoomDO;
import com.bootdo.xjbg.service.RoomService;



@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public RoomDO get(Integer id){
		return roomDao.get(id);
	}
	
	@Override
	public List<RoomDO> list(Map<String, Object> map){
		return roomDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomDao.count(map);
	}
	
	@Override
	public int save(RoomDO room){
		room.setCreateTime(new Date());
		UserDO user = ShiroUtils.getUser();
		room.setCreateUser(user.getUserId());
		return roomDao.save(room);
	}
	
	@Override
	public int update(RoomDO room){
		return roomDao.update(room);
	}
	
	@Override
	public int remove(Integer id){
		return roomDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return roomDao.batchRemove(ids);
	}
	
}
