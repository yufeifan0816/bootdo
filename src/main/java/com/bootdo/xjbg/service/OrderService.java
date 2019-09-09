package com.bootdo.xjbg.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.xjbg.domain.OrderDO;
import com.bootdo.xjbg.vo.OrderVo;
import com.github.pagehelper.Page;

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

	/**
	 * 根据房间id查询开房信息
	 * @param id
	 * @return
	 */
    OrderDO findByRoomId(Integer id);
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //修改开房记录
	 * @Date 16:15 2019/6/5
	 * @Param [order]
	 * @return void
	 **/
	void updateOrder(OrderDO order);
	/***
	 * @Author yufeifan@wondersgroup.com
	 * @Description 退房结账
	 * @Date 16:05 2019/8/7
	 * @Param [orderId]
	 * @return void
	 **/
	OrderDO checkOut(Integer orderId, String checkOutUser, String isDebt)throws Exception;
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //分页查询工单列表
	 * @Date 17:07 2019/8/8
	 * @Param [query]
	 * @return java.util.List<com.bootdo.xjbg.vo.OrderVo>
	 **/
    List<OrderVo> orderList(Map<String, Object> map);
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //续住
	 * @Date 17:55 2019/9/1
	 * @Param [orderId] 工单id
	 * @return com.bootdo.xjbg.domain.OrderDO
	 **/
    OrderDO renew(Long orderId);

	PageUtils pageQuery( Query param);
}
