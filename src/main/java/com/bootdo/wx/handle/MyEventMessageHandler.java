package com.bootdo.wx.handle;

import com.bootdo.wx.domain.WeixinUserDO;
import com.bootdo.wx.service.WeixinUserService;
import com.bootdo.wx.utils.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.event.*;
import org.weixin4j.spi.IEventMessageHandler;

import java.util.Date;
import java.util.List;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-22 10:59
 **/
@Component
public class MyEventMessageHandler implements IEventMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyEventMessageHandler.class);
    @Autowired
    WeixinUserService WeixinUserService;
    @Override
    public OutputMessage subscribe(SubscribeEventMessage msg) {
        List<WeixinUserDO> byOpenId = WeixinUserService.findByOpenId(msg.getFromUserName());
        if(byOpenId==null||byOpenId.size()!=0){
            return WeixinUtils.getSubscribeMsg("蛤蟆皮");
        }
        WeixinUserDO weixinUser = new WeixinUserDO();
        weixinUser.setOpenId(msg.getFromUserName());
        weixinUser.setCreateTime(new Date());
        WeixinUserService.save(weixinUser);
        return WeixinUtils.getSubscribeMsg("关注个锤子你");
    }

    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage location(LocationEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage click(ClickEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage view(ViewEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage msg) {
        return null;
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage msg) {
        return null;
    }
}
