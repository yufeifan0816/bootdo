package com.bootdo.websocket.sevice;

import com.bootdo.common.utils.R;
import org.springframework.ui.Model;

import java.util.ArrayList;

public interface NiuniuService {
     ArrayList<Integer> pk = new ArrayList<>();
     //获取五张牌
      void faPai();
      //洗牌
    void xipai();
    int[]  suanPai(String fivePk);

    void joinRoom(Model model);

    void msgToRoom(String infoStr);

    /***
     * 准备开始
     * @param userId
     */
    void ready(String userId,String cash);

    void cancle(String userId);

    void exit(String userId);

    String tanpai(String userId);
    /**计算输赢*/
    void result();
    /**获取房间状态*/
    R roomstuts();

    void clearScore();
    //踢出玩家
    void tcyx(String userId);
}
