package com.bootdo.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.weixin4j.spi.DefaultMessageHandler;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 17:23
 **/
@Configuration
public class weixinConfig {
    @Bean
    public DefaultMessageHandler MessageHandler(){
        return new DefaultMessageHandler();
    }
}
