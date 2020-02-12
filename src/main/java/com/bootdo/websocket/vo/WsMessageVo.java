package com.bootdo.websocket.vo;

import java.util.List;

/**
 * 封装websocket消息
 */
public class WsMessageVo {

    /**消息类型*/
    /**1登录后消息,2发牌消息*/
   private String msgType;
   private String userName;
   private String userId;
   private String score;
   private Object data;
   private List<WsMessageVo> allInfo;

    public List<WsMessageVo> getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(List<WsMessageVo> allInfo) {
        this.allInfo = allInfo;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
