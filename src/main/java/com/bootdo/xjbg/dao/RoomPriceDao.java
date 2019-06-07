package com.bootdo.xjbg.dao;

import com.bootdo.xjbg.domain.RoomPriceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-06-06 16:47:41
 */
@Mapper
public interface RoomPriceDao {

	RoomPriceDO get(Long roomId);
	
	List<RoomPriceDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoomPriceDO roomPrice);
	
	int update(RoomPriceDO roomPrice);
	
	int remove(Long room_id);
	
	int batchRemove(Long[] roomIds);

    Integer getPrice(Map<String,Object> map);
}
