package com.bootdo.xjbg.service;

import com.bootdo.xjbg.domain.RoomDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yff
 * @email m18271875021@163.com
 * @date 2019-05-24 09:34:36
 */
public interface RoomService {
	
	RoomDO get(Integer id);
	
	List<RoomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomDO room);
	
	int update(RoomDO room);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<RoomDO> getAll();
	/***
	 * @Author yufeifan@wondersgroup.com
	 * @Description //修改房间状态
	 * @Date 16:23 2019/6/5
	 * @Param [roomId, i]
	 * @return void
	 **/
    void alterStrat(Long roomId, int roomState);
}
