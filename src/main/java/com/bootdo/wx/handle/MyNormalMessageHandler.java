package com.bootdo.wx.handle;

import com.bootdo.common.redis.RedisRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.*;
import org.weixin4j.model.message.output.ImageOutputMessage;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

import java.io.FileInputStream;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 17:06
 **/
@Component
public class MyNormalMessageHandler implements INormalMessageHandler {
    @Autowired
    private RedisRegistry redisRegistry;
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 文本消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     * @return org.weixin4j.model.message.OutputMessage
     **/
    @Override
    public OutputMessage textTypeMsg(TextInputMessage textInputMessage) {
        Weixin weixin = new Weixin();
        try {
            String access_token = weixin.getToken().getAccess_token();
            redisRegistry.set("access_token",access_token);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        ImageOutputMessage imageOutputMessage = new ImageOutputMessage();
        return new TextOutputMessage("你的消息已收到");
    }
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 图片消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     * @return org.weixin4j.model.message.OutputMessage
     **/
    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage imageInputMessage) {
        return null;
    }
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 语音消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     * @return org.weixin4j.model.message.OutputMessage
     **/
    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage voiceInputMessage) {
        return null;
    }
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 视频消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     * @return org.weixin4j.model.message.OutputMessage
     **/
    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage videoInputMessage) {
        return null;
    }
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 短视频消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     * @return org.weixin4j.model.message.OutputMessage
     **/
    @Override
    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage shortVideoInputMessage) {
        return null;
    }

    @Override
    public OutputMessage locationTypeMsg(LocationInputMessage locationInputMessage) {
        return null;
    }

    @Override
    public OutputMessage linkTypeMsg(LinkInputMessage linkInputMessage) {
        return null;
    }
}
