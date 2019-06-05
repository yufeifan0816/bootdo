package com.bootdo.xjbg.service;

import com.bootdo.xjbg.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 10:27:48
 */
public interface OrderService {
	
	OrderDO get(Long id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //添加开房记录
	 * @Date 16:15 2019/6/5
	 * @Param [order]
	 * @return void
	 **/
    void addOrder(OrderDO order);
}
