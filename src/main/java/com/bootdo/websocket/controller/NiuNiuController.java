package com.bootdo.websocket.controller;


import com.bootdo.common.utils.R;
import com.bootdo.system.service.UserService;
import com.bootdo.websocket.sevice.NiuniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/niu")
@Controller
public class NiuNiuController {
    @Autowired
    UserService userService;
    @Autowired
    NiuniuService niuniuService;

    @GetMapping("/roomList")
    String roomList(Model model){
        return "niuniu/roomList";
    }
    /**
     * 进入房间
     */
    @RequestMapping("/joinRoom/{roomId}")
    public String joinRoom(Model model,@PathVariable("roomId") String roomId ){
        niuniuService.joinRoom(model,roomId);
        return "niuniu/room";
    }
    /**
     * 推送个人信息到房间,同时获取该房间所有人员信息
     */
    @RequestMapping("/msgToRoom")
    @ResponseBody
    public R msgToRoom(String infoStr ){
        R r = new R();
        niuniuService.msgToRoom(infoStr);
        return r;
    }
    /**
     * 获取房间状态
     */
    @RequestMapping("/roomstuts")
    @ResponseBody
    public Map roomstuts(String[] roomIds){
       Map<String,  Map<String, String>> rooms = new HashMap<>();
        for (String roomId : roomIds) {
            R roomstuts = niuniuService.roomstuts(roomId);
            Map<String, String> room = new HashMap<>();
            room.put("status",(String) roomstuts.get("status"));
            room.put("num",(String) roomstuts.get("num"));
            rooms.put(roomId,room);
        }
        return rooms;
    }
    /**
     * 准备开始游戏
     */
    @RequestMapping("/ready")
    @ResponseBody
    public R ready(String userId,String cash ){
        R r = new R();
        try {
            niuniuService.ready(userId,cash);
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"准备出错");
        }
        return r;
    }
    /**
     * 取消准备
     */
    @RequestMapping("/cancle")
    @ResponseBody
    public R cancle(String userId ){
        R r = new R();
        try {
            niuniuService.cancle(userId);
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"取消准备出错");
        }
        return r;
    }
    /**
     * 退出房间
     */
    @RequestMapping("/exit")
    @ResponseBody
    public R exit(String userId ,Model model ){
        R r = new R();
        try {
            niuniuService.exit(userId,false);
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"退出失败");
        }
        return r;

    }
    /**
     * 摊牌
     */
    @RequestMapping("/tanpai")
    @ResponseBody
    public R tanpai(String userId  ){
        R r = new R();
        try {
            r.put("msg",niuniuService.tanpai(userId));
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"摊牌失败");
        }
        return r;

    }

    /**
     * 清空积分
     */
    @RequestMapping("/clearScore")
    @ResponseBody
    public R clearScore(String roomId){
        R r = new R();
        try {
            niuniuService.clearScore(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"清空积分失败");
        }
        return r;

    }
    /**
     * 踢出指定玩家
     */
    @RequestMapping("/tcyx")
    @ResponseBody
    public R tcyx(String userId){
        R r = new R();
        try {
            niuniuService.tcyx(userId);
        } catch (Exception e) {
            e.printStackTrace();
            r = R.error(501,"踢出失败");
        }
        return r;

    }
}
