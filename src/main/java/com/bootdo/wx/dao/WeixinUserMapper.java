package com.bootdo.wx.dao;

import com.bootdo.wx.domain.WeixinUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WeixinUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeixinUser record);

    int insertSelective(WeixinUser record);

    WeixinUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeixinUser record);

    int updateByPrimaryKey(WeixinUser record);

    List<WeixinUser> findByOpenId(String openId);
}