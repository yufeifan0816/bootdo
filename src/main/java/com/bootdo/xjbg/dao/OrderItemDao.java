package com.bootdo.xjbg.dao;

import com.bootdo.xjbg.domain.OrderDO;
import com.bootdo.xjbg.domain.OrderItemDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 10:27:48
 */
@Mapper
public interface OrderItemDao {

	OrderItemDO get(Long id);
	
	List<OrderItemDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrderItemDO orderItem);
	
	int update(OrderItemDO orderItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<OrderItemDO> findByOrderId(Integer roomId);
}
