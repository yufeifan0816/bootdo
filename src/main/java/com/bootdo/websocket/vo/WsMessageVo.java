package com.bootdo.websocket.vo;

import java.util.List;
import java.util.Map;

/**
 * 封装websocket消息
 */
public class WsMessageVo {

    /**消息类型*/
    /**1接收单个玩家信息,
     * 2接收多个玩家信息,
     * 3接收自己的牌,
     * 4,接收退出游戏的人员id
     * 5,接收其他玩家牌信息
     * 6,接收结算面板信息
     * */
   private String msgType;
   private String userName;
   private String userId;
   private String score;
   private String isReady="0";
   private String isZj="0";
   private String cash;
   private String pk5;
   private String result;
   private String newZjId;
   private Map<String, Integer> jiesuanMsg;
   private Object data;
   private String tishiMsg;
   private List<WsMessageVo> allInfo;

    public Map<String, Integer> getJiesuanMsg() {
        return jiesuanMsg;
    }

    public String getTishiMsg() {
        return tishiMsg;
    }

    public void setTishiMsg(String tishiMsg) {
        this.tishiMsg = tishiMsg;
    }

    public void setJiesuanMsg(Map<String, Integer> jiesuanMsg) {
        this.jiesuanMsg = jiesuanMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPk5() {
        return pk5;
    }

    public void setPk5(String pk5) {
        this.pk5 = pk5;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getNewZjId() {
        return newZjId;
    }

    public void setNewZjId(String newZjId) {
        this.newZjId = newZjId;
    }

    public String getIsZj() {
        return isZj;
    }

    public void setIsZj(String isZj) {
        this.isZj = isZj;
    }

    public String getIsReady() {
        return isReady;
    }

    public void setIsReady(String isReady) {
        this.isReady = isReady;
    }

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
