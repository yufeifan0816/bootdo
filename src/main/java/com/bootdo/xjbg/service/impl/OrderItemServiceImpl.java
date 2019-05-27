package com.bootdo.xjbg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.dao.OrderItemDao;
import com.bootdo.xjbg.domain.OrderItemDO;
import com.bootdo.xjbg.service.OrderItemService;



@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public OrderItemDO get(Long id){
		return orderItemDao.get(id);
	}
	
	@Override
	public List<OrderItemDO> list(Map<String, Object> map){
		return orderItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderItemDao.count(map);
	}
	
	@Override
	public int save(OrderItemDO orderItem){
		return orderItemDao.save(orderItem);
	}
	
	@Override
	public int update(OrderItemDO orderItem){
		return orderItemDao.update(orderItem);
	}
	
	@Override
	public int remove(Long id){
		return orderItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderItemDao.batchRemove(ids);
	}
	
}
