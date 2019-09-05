package com.bootdo.xjbg.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.xjbg.dao.OrderItemDao;
import com.bootdo.xjbg.dao.RoomDao;
import com.bootdo.xjbg.domain.OrderItemDO;
import com.bootdo.xjbg.service.RoomService;
import com.bootdo.xjbg.vo.OrderVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	 private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RoomDao roomDao;
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
	@Transactional
    public void addOrder(OrderDO order) {
		order.setOrderState("1");//当前工单
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

	/***
	 *根据房间id查询开房信息
	 * @param id
	 * @return
	 */
	@Override
	public OrderDO findByRoomId(Integer id) {
		OrderDO orderDO = orderDao.findByRoomId(id);
		List<OrderItemDO> OrderItems = orderItemDao.findByOrderId(orderDO.getId().intValue());
		orderDO.setOrderItems(OrderItems);
		logger.info(JSONObject.toJSONString(orderDO));
		return orderDO;
	}

	@Override
	@Transactional
	public void updateOrder(OrderDO order) {
		List<OrderItemDO> orderItems = order.getOrderItems();
		order.setUpdateTime(new Date());
		order.setUpdateUser(ShiroUtils.getUserName());
		orderDao.update(order);
		for (OrderItemDO orderItem : orderItems) {
			Long id = orderItem.getId();
			if(id ==null||id ==0L){
				orderItem.setCreateUser(ShiroUtils.getUserName());
				orderItem.setCreateTime(new Date());
				orderItem.setOrderId(order.getId());
				orderItemDao.save(orderItem);
			}else{
				orderItem.setUpdateTime(new Date());
				orderItem.setUpdateUser(ShiroUtils.getUserName());
				orderItemDao.update(orderItem);
			}


		}
		//修改房间状态
		roomService.alterStrat(order.getRoomId(),3);
	}
	/**退房结账**/
	@Override
	@Transactional
	public OrderDO checkOut(Integer orderId, String checkOutUser, String isDebt){
		OrderDO orderDO = orderDao.get(Long.valueOf(orderId));
		List<OrderItemDO> OrderItems = orderItemDao.findByOrderId(orderDO.getId().intValue());
		orderDO.setOrderItems(OrderItems);
		//修改房间状态为空房
		roomService.alterStrat(orderDO.getRoomId(),1);
		//修改工单状态为已完成
		orderDO.setOrderState("2");
		orderDO.setCheckOutUser(checkOutUser);
		orderDO.setIsDebt(isDebt);
		orderDO.setCheckOutTime(new Date());
		orderDao.update(orderDO);
		//修改房间状态
		return orderDO;
	}

	@Override
	public List<OrderVo> orderList(Map<String, Object> map) {
		return orderDao.orderList(map);
	}
	/**续住天数加1*/
	@Transactional
	@Override
	public OrderDO renew(Long orderId) {
		OrderDO orderDO = orderDao.get(orderId);
		if(orderDO.getDays()==null){
			orderDO.setDays(2);
		}else{
			orderDO.setDays(orderDO.getDays()+1);
		}
        int update = orderDao.update(orderDO);
    if(update!=1){
        logger.info("修改数据失败");
        throw new RuntimeException("操作失败");
    }
        return orderDO;
	}

	@Override
	public Page<OrderVo> pageQuery(int offset,int limit,Map param) {
		PageHelper.startPage(offset,limit);
		Page<OrderVo> orderVos = (Page<OrderVo>) orderDao.orderList(param);
		List list = orderDao.orderList(param);
		System.out.println("Page size = "+orderVos.size());
		System.out.println("list size = "+list.size());
		return orderVos;
	}

}
