package com.bootdo.websocket.sevice;

import org.springframework.ui.Model;

import java.util.ArrayList;

public interface NiuniuService {
     ArrayList<Integer> pk = new ArrayList<>();
     //获取五张牌
      String getFivePk();
      //洗牌
    void xipai();
    int[]  suanPai(String fivePk);

    void joinRoom(Model model);

    void msgToRoom(String infoStr);
}
