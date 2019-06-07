package com.bootdo.xjbg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.dao.RoomPriceDao;
import com.bootdo.xjbg.domain.RoomPriceDO;
import com.bootdo.xjbg.service.RoomPriceService;



@Service
public class RoomPriceServiceImpl implements RoomPriceService {
	@Autowired
	private RoomPriceDao roomPriceDao;
	
	@Override
	public RoomPriceDO get(Long roomId){
		return roomPriceDao.get(roomId);
	}
	
	@Override
	public List<RoomPriceDO> list(Map<String, Object> map){
		return roomPriceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomPriceDao.count(map);
	}
	
	@Override
	public int save(RoomPriceDO roomPrice){
		return roomPriceDao.save(roomPrice);
	}
	
	@Override
	public int update(RoomPriceDO roomPrice){
		return roomPriceDao.update(roomPrice);
	}
	
	@Override
	public int remove(Long roomId){
		return roomPriceDao.remove(roomId);
	}
	
	@Override
	public int batchRemove(Long[] roomIds){
		return roomPriceDao.batchRemove(roomIds);
	}
	/**根据房间id 和入住类型查询价格*/
	@Override
	public Integer getPrice(Long roomId, String orderType) {
		Map<String, Object> param = new HashMap<>();
		param.put("roomId",roomId);
		param.put("orderType",orderType);
		return roomPriceDao.getPrice(param);
	}

}
