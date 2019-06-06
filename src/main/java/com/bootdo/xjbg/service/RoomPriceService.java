package com.bootdo.xjbg.service;

import com.bootdo.xjbg.domain.RoomPriceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-06-06 16:47:41
 */
public interface RoomPriceService {
	
	RoomPriceDO get(Long roomId);
	
	List<RoomPriceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomPriceDO roomPrice);
	
	int update(RoomPriceDO roomPrice);
	
	int remove(Long roomId);
	
	int batchRemove(Long[] roomIds);
}
