package com.bootdo.websocket;

import com.bootdo.websocket.sevice.NiuniuService;
import com.bootdo.websocket.sevice.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/ws")
public class webSocketController  {
    @Autowired
    private NiuniuService niuniuService;

    private  static ArrayList<Integer> pk = new ArrayList<>();
    @RequestMapping
    String Order(Model model){
        return "webSocket/socket1";
    }
    @RequestMapping("/niu")
    String niuniu(Model model){
        return "webSocket/niuniu";
    }

    @ResponseBody
    @RequestMapping("/fapai")
    public Map fapai(String message) {
        Map result = new HashMap();
//        try {
//            String fivePk = niuniuService.getFivePk();
//            WebsocketService.sendInfo(fivePk);
//            result.put("code", 200);
//            result.put("msg", niuniuService.suanPai(fivePk));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;
    }
    //推送数据接口
    @ResponseBody
    @PostMapping("/send")
    public Map pushToWeb(String cid, String message) {
        Map result = new HashMap();

            WebsocketService.sendInfo(message,cid);
            result.put("code", 200);
            result.put("msg", "success");
        return result;
    }
    public static void xipai(){
        pk = new ArrayList<>();
        for (int i = 1; i < 55; i++) {
            pk.add(i);
        }
    }
    /**
     * 随机获取5张牌
     */
    public static String getFivePk(){
        xipai();
        String pks = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(pk.size());
            pks+=pk.get(n)+"|";
            pk.remove(n);
    }
                return pks;
    }


}
