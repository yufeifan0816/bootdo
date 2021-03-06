package com.bootdo.system.dao;

import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();
	/***
	 * @Author yufeifan@wondersgroup.com
	 * @Description 所有用户
	 * @Date 13:35 2019/5/24
	 * @return java.util.List<com.bootdo.system.domain.UserDO>
	 **/
	List<UserDO> listAllUser();

	int queryScoreByUsrId(int userId);
	/**添加玩家钱数*/
	int addMoney(@Param("userId") String userId, @Param("score")Integer money);
	/**清空积分*/
	int clearScore();

    int clearScore2(List<String> roomPeople);

    void insertGameScore(Long userId);
}
