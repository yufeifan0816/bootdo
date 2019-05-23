package com.bootdo.weixin.dao;

import com.bootdo.weixin.domain.WeixinUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 关注微信公众号的用户信息
 * @author yff
 * @email 1992lcg@163.com
 * @date 2019-05-22 17:51:52
 */
@Mapper
public interface WeixinUserDao {

	WeixinUserDO get(Long id);
	
	List<WeixinUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WeixinUserDO weixinUser);
	
	int update(WeixinUserDO weixinUser);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<WeixinUserDO> findByOpenId(String fromUserName);
}
