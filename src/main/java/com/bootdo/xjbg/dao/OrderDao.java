package com.bootdo.xjbg.dao;

import com.bootdo.xjbg.domain.OrderDO;

import java.util.List;
import java.util.Map;

import com.bootdo.xjbg.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-27 10:27:48
 */
@Mapper
public interface OrderDao {

	OrderDO get(Long id);
	
	List<OrderDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);

	Long save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	/***
	 *根据房间id查询开房信息
	 * @param roomId
	 * @return
	 */
    OrderDO findByRoomId(Integer roomId);
	/**
	 * @Author yufeifan@wondersgroup.com
	 * @Description //查询工单列表
	 * @Date 17:10 2019/8/8
	 * @Param [map]
	 * @return java.util.List<com.bootdo.xjbg.vo.OrderVo>
	 **/
	List<OrderVo> orderList(Map<String,Object> map);
}
