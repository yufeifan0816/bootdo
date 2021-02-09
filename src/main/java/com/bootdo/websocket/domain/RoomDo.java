package com.bootdo.websocket.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 房间DO
 * */
public class RoomDo {
    /**房间id*/
    private String roomId;
    /**房间人数*/
    private String roomPeopleNum;
    /**房间状态0未开始,1开始*/
    private String roomStatus;
    /**准备人员Id*/
    public  List<String> readyUserId =  new Vector<>();
    /**摊牌人员Id*/
    public  List<String> tanpaiPeople = new Vector<>();
    /**房间人员Id*/
    public   List<String> roomPeople = new Vector<>();
    /**押金*/
    public  Map<String,String> cashs = new ConcurrentHashMap<>();
    /**房间庄家Id*/
    public   Map<String,String> pai = new HashMap<>();
    private  String zj = "";
    /**
     * 获取房间其他人id
     * **/
    public List<String> getOtherIds(String userId){
        List<String> otherIds = new Vector<String>();
        for (String roomPerson : roomPeople) {
            if(!roomPerson.equals(userId)){
                otherIds.add(roomPerson);
            }
        }
        return otherIds;
    }
    public void addReadyUserId(String userId){
        if(!readyUserId.contains(userId)){
            tanpaiPeople.add(userId);
        }
    }
    public void addTanpaiPeople(String userId){
        if(!tanpaiPeople.contains(userId)){
            tanpaiPeople.add(userId);
        }
    }
    public void addRoomPeople(String userId){
        if(!roomPeople.contains(userId)){
            roomPeople.add(userId);
        }
    }

    public Map<String, String> getPai() {
        return pai;
    }

    public void setPai(Map<String, String> pai) {
        this.pai = pai;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomPeopleNum() {
        return roomPeopleNum;
    }

    public void setRoomPeopleNum(String roomPeopleNum) {
        this.roomPeopleNum = roomPeopleNum;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<String> getReadyUserId() {
        return readyUserId;
    }

    public void setReadyUserId(List<String> readyUserId) {
        this.readyUserId = readyUserId;
    }

    public List<String> getTanpaiPeople() {
        return tanpaiPeople;
    }

    public void setTanpaiPeople(List<String> tanpaiPeople) {
        this.tanpaiPeople = tanpaiPeople;
    }

    public List<String> getRoomPeople() {
        return roomPeople;
    }

    public void setRoomPeople(List<String> roomPeople) {
        this.roomPeople = roomPeople;
    }

    public Map<String, String> getCashs() {
        return cashs;
    }

    public void setCashs(Map<String, String> cashs) {
        this.cashs = cashs;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }
}
