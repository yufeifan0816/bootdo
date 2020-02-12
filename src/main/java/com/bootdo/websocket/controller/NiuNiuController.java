package com.bootdo.websocket.controller;


import com.bootdo.common.utils.R;
import com.bootdo.system.service.UserService;
import com.bootdo.websocket.sevice.NiuniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/niu")
@Controller
public class NiuNiuController {
    @Autowired
    UserService userService;
    @Autowired
    NiuniuService niuniuService;

    @GetMapping("/roomList")
    String roomList(Model model){
        return "/niuniu/roomList";
    }
    /**
     * 进入房间
     */
    @RequestMapping("/joinRoom")
    public String joinRoom(Model model ){
        niuniuService.joinRoom(model);
        return "/niuniu/room";
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
}
