package com.bootdo.wx.task;

import com.bootdo.common.redis.RedisRegistry;
import com.bootdo.common.redis.shiro.RedisManager;
import com.bootdo.common.redis.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 18:15
 **/
@Component
public class wxTask {
    @Autowired
    private RedisRegistry redisRegistry;
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void job1() throws WeixinException {
        Weixin weixin = new Weixin();
        String access_token = weixin.getToken().getAccess_token();
        redisRegistry.set("access_token",access_token);
    }
}
