package com.bootdo.weixin.handle;

import com.bootdo.common.redis.RedisRegistry;
import com.bootdo.weixin.utils.PicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.component.MediaComponent;
import org.weixin4j.model.message.Image;
import org.weixin4j.model.message.MediaType;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.*;
import org.weixin4j.model.message.output.ImageOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.UUID;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-20 17:06
 **/
@Component
public class MyNormalMessageHandler implements INormalMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyNormalMessageHandler.class);
    private static String separator =System.getProperty("file.separator");
    private static Weixin weixin = new Weixin();
    @Autowired
    RedisRegistry redisRegistry;

    /**
     * 初始化的时候获取access_token 此后定时任务只能,每隔2小时获取一次
     */
    @PostConstruct
    public void init() throws WeixinException {
    }
    /**
     * @return org.weixin4j.model.message.OutputMessage
     * @Author yufeifan@wondersgroup.com
     * @Description 文本消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     **/
    @Override
    public OutputMessage textTypeMsg(TextInputMessage textInputMessage) {
        logger.info("ClientUser:"+textInputMessage.getFromUserName());
        MediaComponent media = weixin.media();
        String content = textInputMessage.getContent();
        String picPath = "yff"+separator+"wxpic"+separator+"123.png";
        String picPath2 = "yff"+separator+"wxpic"+UUID.randomUUID() +".png";
        PicUtils.DfAddWaterMark(picPath,picPath2,content);
        File file = new File(PicUtils.fDir,picPath2);
        String mediaId="";
        if(logger.isInfoEnabled()){
            logger.info("path1:"+picPath);
            logger.info("path2:"+picPath2);
        }
        if(file.isFile() && file.exists()){
            try {
                 mediaId = media.upload(MediaType.Image, file);
                file.delete();
                logger.info("mediaId:" + mediaId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ImageOutputMessage imageOutputMessage = new ImageOutputMessage();
        Image image = new Image();
        image.setMediaId(mediaId);
        imageOutputMessage.setImage(image);
        return imageOutputMessage;
    }

    /**
     * @return org.weixin4j.model.message.OutputMessage
     * @Author yufeifan@wondersgroup.com
     * @Description 图片消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     **/
    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage imageInputMessage) {
        return null;
    }

    /**
     * @return org.weixin4j.model.message.OutputMessage
     * @Author yufeifan@wondersgroup.com
     * @Description 语音消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     **/
    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage voiceInputMessage) {
        return null;
    }

    /**
     * @return org.weixin4j.model.message.OutputMessage
     * @Author yufeifan@wondersgroup.com
     * @Description 视频消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
     **/
    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage videoInputMessage) {
        return null;
    }

    /**
     * @return org.weixin4j.model.message.OutputMessage
     * @Author yufeifan@wondersgroup.com
     * @Description 短视频消息接收
     * @Date 17:46 2019/5/20
     * @Param [textInputMessage]
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
