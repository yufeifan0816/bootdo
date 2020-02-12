package com.bootdo.websocket.sevice.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.websocket.sevice.NiuniuService;
import com.bootdo.websocket.sevice.WebsocketService;
import com.bootdo.websocket.vo.WsMessageVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class NiuniuServiceImpl implements NiuniuService {

    @Autowired
    private UserDao userDao;
    @Resource
    private WebsocketService websocketService;
    @Override
    /**
     * 随机获取5张牌
     */
    public  String getFivePk(){
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

    /**
     * 洗牌
     */
    @Override
    public void xipai() {
        pk.clear();
        for (int i = 1; i < 53; i++) {
            pk.add(i);
        }
    }

    @Override
    public int[] suanPai(String fivePk) {
        //数组第一个表示有无牛，第二个表示牛几，第三个为最大的牌
        int[] result = new int[3];
        //将牌好转成int数组方便计算
        String[] pk5Str = fivePk.split("\\|");
        Integer[] pk5 = new Integer[5];
        for (int i = 0; i <pk5Str.length ; i++) {
            pk5[i]=Integer.parseInt(pk5Str[i]);
        }
        //获取最大的牌
        int max = pk5[0];
        for (int i1 = 1; i1 < pk5.length; i1++) {
            if(max<pk5[i1])  max=pk5[i1];
        }
        //三层循环计算是牛牛
        for (int i = 0; i <pk5.length ; i++) {
            int one = pk5[i];

            for (int j = 0; j <pk5.length ; j++) {
                int two = pk5[j];
                if(two==one)continue;
                for (int k = 0; k <pk5.length ; k++) {
                    int three = pk5[k];
                    if(three==one||three==two)continue;
                    int pkValueByNum1 = getPkValueByNum(one);
                    int pkValueByNum2 = getPkValueByNum(two);
                    int pkValueByNum3 = getPkValueByNum(three);
                    int sum = pkValueByNum1+pkValueByNum2+pkValueByNum3;
                    if(sum%10==0){
                        //有牛
                        result[0] = 1;
                        result[1] = getNiuNum(pk5,one,two,three);
                        result[1] = max;
                        return result;
                    }
                }
            }
        }

        result[0] = 0;
        result[1] = max;
        //////
        if(result[0]==0){
            System.out.println("("+getPkValueByNum(pk5[0])+","+getPkValueByNum(pk5[1])+","+getPkValueByNum(pk5[2])+","+getPkValueByNum(pk5[3])+","+getPkValueByNum(pk5[4])+"）"+result[1]+"大");
        }else{
            System.out.println("("+getPkValueByNum(pk5[0])+","+getPkValueByNum(pk5[1])+","+getPkValueByNum(pk5[2])+","+getPkValueByNum(pk5[3])+","+getPkValueByNum(pk5[4])+"）"+"牛"+result[1]);

        }

        return result;
    }

    @Override
    public void joinRoom(Model model) {
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("userId",user.getUserId());
        model.addAttribute("userName",user.getName());
        model.addAttribute("score",userDao.queryScoreByUsrId(user.getUserId().intValue()));
        //进入房间后提示信息群发
        Map<String, Object> info = model.asMap();
        String infoStr = JSONObject.toJSONString(info);
        model.addAttribute("infoStr",infoStr);
       // WebsocketService.sendInfo(infoStr,null);

    }

    @Override
    public void msgToRoom(String infoStr) {
        JSONObject jsonObject = JSONObject.parseObject(infoStr);
        String userId = StringUtils.toString(jsonObject.get("userId"));
        //推送个人信息到房间2
        WsMessageVo wsMessageVo1 = new WsMessageVo();
        wsMessageVo1.setMsgType("1");
        wsMessageVo1.setUserName(StringUtils.toString(jsonObject.get("userName")));
        wsMessageVo1.setUserId(userId);
        wsMessageVo1.setScore(StringUtils.toString(jsonObject.get("score")));
        WebsocketService.sendInfo(JSONObject.toJSONString(wsMessageVo1),null);
        //获取该房间所有人员信息1
        WsMessageVo wsMessageVo2 = new WsMessageVo();
        wsMessageVo2.setMsgType("2");
        wsMessageVo2.setAllInfo(getAllInfoByRoom());
        WebsocketService.sendInfo(JSONObject.toJSONString(wsMessageVo2),userId);
    }

    public List<WsMessageVo> getAllInfoByRoom(){
        ArrayList<WsMessageVo> allInfo = new ArrayList<>();
        List<String> allSid = websocketService.getAllSid();
        for (String sid : allSid) {
            UserDO userDO = userDao.get(Long.valueOf(sid));
            int score = userDao.queryScoreByUsrId(Integer.valueOf(sid));
            WsMessageVo info = new WsMessageVo();
            info.setUserId(userDO.getUserId().toString());
            info.setUserName(userDO.getName());
            info.setScore(String.valueOf(score));
            allInfo.add(info);
        }
        return allInfo;
    }

    public int getPkValueByNum(int num){
        //12345...依次方1，梅花1，杏花1，桃花1，方花2.....
        if(num<41){
            return (num-1)/4+1;
        }
        return 10;
    }
    //计算牛数
    public int getNiuNum(Integer[] pk5,int one,int two,int three){
        int sum = 0;
        for (int i = 0; i < pk5.length; i++) {
            if(pk5[i]!=one&&pk5[i]!=two&&pk5[i]!=three){
               sum+=getPkValueByNum(pk5[i]);
            }
        }
        return sum%10;
    }
}
