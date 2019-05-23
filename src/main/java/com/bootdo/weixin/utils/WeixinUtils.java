package com.bootdo.weixin.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weixin4j.Weixin;
import org.weixin4j.component.MediaComponent;
import org.weixin4j.model.message.Image;
import org.weixin4j.model.message.MediaType;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.output.ImageOutputMessage;
import org.weixin4j.model.message.output.TextOutputMessage;

import java.io.File;
import java.util.UUID;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-22 11:20
 **/
public class WeixinUtils {
    private static final Logger logger = LoggerFactory.getLogger(WeixinUtils.class);
    private static  Weixin weixin  ;

    public synchronized static Weixin getWeixinInstance() {
        if (weixin == null) {
            weixin = new Weixin();
        }
        return weixin;
    }

    public static OutputMessage getSubscribeMsg(String msg) {
        String picPath = "D:\\yff\\gt\\2.png";
        String picPath2 = "D:\\yff\\gt\\" + UUID.randomUUID() + ".png";
        PicUtils.DfAddWaterMark(picPath, picPath2, msg);
        return updatePicRtMsg(picPath2);
    }
    public static OutputMessage updatePicRtMsg(String picPath){
        String mediaId = updatePic(picPath);
        if (StringUtils.isBlank(mediaId)){
            TextOutputMessage textOutputMessage = new TextOutputMessage();
            textOutputMessage.setContent("文件不存在");
            return textOutputMessage;
        }
        ImageOutputMessage imageOutputMessage = new ImageOutputMessage();
        Image image = new Image();
        image.setMediaId(mediaId);
        imageOutputMessage.setImage(image);
        return imageOutputMessage;

    }
    public static String updatePic(String picPath){
        MediaComponent media = getWeixinInstance().media();
        File file = new File(picPath);
        String mediaId = "";
        if (file.isFile() && file.exists()) {
            try {
                mediaId = media.upload(MediaType.Image, file);
                logger.info("mediaId:" + mediaId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  mediaId;
    }
}
