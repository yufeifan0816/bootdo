package com.bootdo.xjbg.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.xjbg.dao.OrderItemDao;
import com.bootdo.xjbg.domain.OrderItemDO;
import com.bootdo.xjbg.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.dao.OrderDao;
import com.bootdo.xjbg.domain.OrderDO;
import com.bootdo.xjbg.service.OrderService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private RoomService roomService;

	@Override
	public OrderDO get(Long id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
    @Transactional
	public int save(OrderDO order){
        return 0;
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Long id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderDao.batchRemove(ids);
	}
    /**开房**/
    @Override
    public void addOrder(OrderDO order) {

        List<OrderItemDO> orderItems = order.getOrderItems();
        order.setCreateTime(new Date());
        order.setCreateUser(ShiroUtils.getUserName());
        Long result = orderDao.save(order);
        for (OrderItemDO orderItem : orderItems) {
            orderItem.setCreateUser(ShiroUtils.getUserName());
            orderItem.setCreateTime(new Date());
            orderItem.setOrderId(order.getId());
            orderItemDao.save(orderItem);
        }
        //修改房间状态
        roomService.alterStrat(order.getRoomId(),3);
    }

}
