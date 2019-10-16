package com.bootdo.weixin.config;

import com.bootdo.weixin.controller.WeixinUserController;
import com.bootdo.weixin.handle.MyEventMessageHandler;
import com.bootdo.weixin.handle.MyNormalMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.weixin4j.spi.DefaultMessageHandler;

import javax.annotation.Resource;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 17:23
 **/
@Configuration
public class weixinConfig {
    @Resource
    MyNormalMessageHandler myNormalMessageHandler;
    @Resource
    MyEventMessageHandler myEventMessageHandler;
    @Bean
    public DefaultMessageHandler MessageHandler(){
        return new DefaultMessageHandler(myNormalMessageHandler, myEventMessageHandler);
    }
}
