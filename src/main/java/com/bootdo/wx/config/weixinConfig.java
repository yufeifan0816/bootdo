package com.bootdo.wx.config;

import com.bootdo.wx.handle.MyEventMessageHandler;
import com.bootdo.wx.handle.MyNormalMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.weixin4j.spi.DefaultMessageHandler;
import org.weixin4j.spi.HandlerFactory;
import org.weixin4j.spi.INormalMessageHandler;

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
