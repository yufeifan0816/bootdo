package com.bootdo.websocket.sevice.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.R;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.websocket.domain.RoomDo;
import com.bootdo.websocket.sevice.NiuniuService;
import com.bootdo.websocket.sevice.WebsocketService;
import com.bootdo.websocket.vo.WsMessageVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Service
public class NiuniuServiceImpl implements NiuniuService {
    public static List<RoomDo> rooms = new Vector<>();

//    public static List<String> readyUserId =  new Vector<>();
//    public static List<String> tanpaiPeople = new Vector<>();
//    public static  List<Long> roomPeople = new Vector<>();
//    public static  Map<String,String> cashs = new ConcurrentHashMap<>();
//    public static  Map<String,String> pai = new HashMap<>();
//    private  Long zj = null;
    @Autowired
    private UserDao userDao;
    /**
     * 发牌
     */
    @Override
    public  void faPai(RoomDo room){
        xipai();
        Random random = new Random();
        List<String> roomPeople = room.getRoomPeople();
        Map<String, String> pai = room.getPai();
        for (String userId : roomPeople) {
            String pks = "";
            for (int i = 0; i < 5; i++) {
                int n = random.nextInt(pk.size());
                pks+=pk.get(n)+"|";
                pk.remove(n);
            }
            //将牌发到各自玩家手里
            WsMessageVo wsMessageVo = new WsMessageVo();
            wsMessageVo.setMsgType("3");
            wsMessageVo.setUserId(userId);
            wsMessageVo.setPk5(pks);
            WebsocketService.sendInfo(JSONObject.toJSONString(wsMessageVo),userId);
            //将牌保存到后台
            pai.put(userId,pks);


        }


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
        //数组第一个表示有无牛0无牛,1有牛,2五花,3炸弹，第二个表示牛几，第三个为最大的牌
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
        //计算特殊牌 炸弹
        HashMap<Integer,Integer> zdMap = new HashMap<>();
        int zdMaxPk=0;
        for (int i = 0; i <pk5.length ; i++) {
            //有四张相同的就是炸弹
            int pkValueByNum = (pk5[i]-1)/4+1;
            Integer value = zdMap.get(pkValueByNum);
            if(value==null)value=0;
            zdMap.put(pkValueByNum,value+1);
        }
        Set<Map.Entry<Integer, Integer>> entries = zdMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value==4){
                result[0]=3;
                result[3] =key;
            }
        }
        //计算特殊牌 五花
        for (int i = 0; i <pk5.length ; i++) {
            //所有牌都大于41的就是五花牛
            if(pk5[i]<41){
                break;
            }
            if (i==4){
                result[0]=2;
                result[2]=max;
                return result;
            }
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
                        result[2] = max;
                        return result;
                    }
                }
            }
        }

        result[0] = 0;
        result[2] = max;
        return result;
    }

    @Override
    public void joinRoom(Model model,String roomId) {
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getUserId();
        model.addAttribute("userId",user.getUserId());
        model.addAttribute("userName",user.getName());
        model.addAttribute("roomId",Integer.valueOf(roomId));
        model.addAttribute("score",userDao.queryScoreByUsrId(user.getUserId().intValue()));
        //进入房间后提示信息群发
        Map<String, Object> info = model.asMap();
        String infoStr = JSONObject.toJSONString(info);
        model.addAttribute("infoStr",infoStr);
        //① 判断房间是否存在
        RoomDo roomDo = getRoomByRoomId(roomId);
        if(roomDo!=null){
            //房间存在
            roomDo.addRoomPeople(userId.toString());
        }else{
            //房间不存在,创建房间
             roomDo = new RoomDo();
            roomDo.setRoomId(roomId);
            roomDo.addRoomPeople(userId.toString());
            roomDo.setZj(userId.toString());
            rooms.add(roomDo);
            model.addAttribute("isZj","1");
        }
        //第一个进入房间的人做庄家
        /*if(roomPeople.size()==0||(roomPeople.size()==1&&(roomPeople.get(0).compareTo(user.getUserId())==0))){
            zj=user.getUserId();
            model.addAttribute("isZj","1");
        }else if(zj.compareTo(user.getUserId())==0) {
            model.addAttribute("isZj","1");
        }else{
            model.addAttribute("isZj","0");
        }
        if(!roomPeople.contains(user.getUserId())){
                //添加房间人数
                roomPeople.add(user.getUserId());
        }*/


    }
    /**
     * 根据用户id获取房间
     * **/
    public static RoomDo getRoomByUserId(String userId) {
        for (RoomDo room : rooms) {
            List<String> roomPeople = room.getRoomPeople();
            if(roomPeople.contains(userId)){
                return room;
            }
        }
        return null;
    }
    /**
     * 根据房间号获取房间do
     * */
    public RoomDo getRoomByRoomId(String roomId) {
        //根据房间号获取房间
        for (RoomDo room : rooms) {
            if( room.getRoomId().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    @Override
    public void msgToRoom(String infoStr) {
        JSONObject jsonObject = JSONObject.parseObject(infoStr);
        String userId = StringUtils.toString(jsonObject.get("userId"));
        RoomDo room = getRoomByUserId(userId);
        String zj = room.getZj();
        //推送个人信息到房间
        WsMessageVo wsMessageVo1 = new WsMessageVo();
        wsMessageVo1.setMsgType("1");
        wsMessageVo1.setUserName(StringUtils.toString(jsonObject.get("userName")));
        wsMessageVo1.setUserId(userId);
        if(userId.equals(zj)){
            wsMessageVo1.setIsZj("1");
        }
        wsMessageVo1.setScore(StringUtils.toString(jsonObject.get("score")));
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo1),room.getOtherIds(userId));
        //获取该房间所有人员信息除了自己1
        WsMessageVo wsMessageVo2 = new WsMessageVo();
        wsMessageVo2.setMsgType("2");
        List<WsMessageVo> allInfoByRoom = getAllInfoByRoom(room);
        for (Iterator<WsMessageVo> ite = allInfoByRoom.iterator(); ite.hasNext();) {
            WsMessageVo wsMessageVo = ite.next();
            if (wsMessageVo.getUserId().equals(userId)){
                ite.remove();
                continue;
            }
        }
        /**错误的迭代删除方式会报错ConcurrentModificationException*/
     /*   for (WsMessageVo wsMessageVo : allInfoByRoom) {
            if (wsMessageVo.getUserId().equals(userId)){
                allInfoByRoom.remove(wsMessageVo);
                continue;
            }
        }*/

        wsMessageVo2.setAllInfo(allInfoByRoom);
        WebsocketService.sendInfo(JSONObject.toJSONString(wsMessageVo2),userId);
    }

    @Override
    public void ready(String userId,String cash) {
        //准备前检查当前是否断开房间
        RoomDo room = getRoomByUserId(userId);
        List<String> roomPeople = room.getRoomPeople();
        List<String> readyUserId = room.getReadyUserId();
        Map<String, String> cashs = room.getCashs();
        if(isOff(userId,room)){
            roomPeople.add(userId);
        }
        //①推送准备信息
        WsMessageVo msgByUserId = getMsgByUserId(userId);
        msgByUserId.setCash(cash);
        msgByUserId.setIsReady("1");
        msgByUserId.setMsgType("1");
        //发送给房间其他玩家
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(msgByUserId),room.getOtherIds(userId));
        //②全局变量记录准备人
        if(!readyUserId.contains(userId)){
            readyUserId.add(userId);
        }
        //③保存押金
        cashs.put(userId,cash);
        //④判断是否所有玩家都准备好,都准备好就开始发牌
        if(roomPeople.size()==readyUserId.size()&&readyUserId.size()>1){
            //发牌
            faPai(room);
        }

    }

    /***
     * 判断是否离开房间(断开房间连接)
     * @param userId
     * @return
     */
    private boolean isOff(String userId, RoomDo room) {
        List<String> roomPeople = room.getRoomPeople();
        if(roomPeople.contains(userId)){
            return false;
        }
        return true;
    }

    /**
     * 取消贮备
     * @param userId
     */
    @Override
    public void cancle(String userId) {
        RoomDo room = getRoomByUserId(userId);
        Map<String, String> cashs = room.getCashs();
        List<String> readyUserId = room.getReadyUserId();
        List<String> roomPeople = room.getRoomPeople();
        //准备钱检查当前是否断开房间
        if(isOff(userId,room)){
            roomPeople.add(userId);
        }
        WsMessageVo msgByUserId = getMsgByUserId(userId);
        msgByUserId.setIsReady("0");
        msgByUserId.setMsgType("1");
        cashs.remove(userId);
        WebsocketService.sendOtherInfo(JSONObject.toJSONString(msgByUserId),userId);
        readyUserId.remove(userId);
    }

    @Override
    public void exit(String userId,boolean isYczz) {
        RoomDo room = getRoomByUserId(userId);
        if(room==null){
            System.out.println("玩家"+userId+"已退出");
            return;
        }

        List<String> readyUserId = room.getReadyUserId();
        List<String> roomPeople = room.getRoomPeople();
        String zj = room.getZj();
        WsMessageVo wsMessageVo = new WsMessageVo();
        //①清除准备人员信息
        if(readyUserId.contains(userId)){
            readyUserId.remove(userId);
        }
        //②清除房间人员信息
        int zjIdex = getKeyByValue(roomPeople, userId);
        roomPeople.remove(userId);

        if(roomPeople.size()==0){
            rooms.remove(room);
        }else if(userId.equals(zj)){
            //如果庄家退出有戏则轮庄
            if(zjIdex==(roomPeople.size())){
                    room.setZj(roomPeople.get(0));
            }else {
                room.setZj(roomPeople.get(zjIdex));
            }
            wsMessageVo.setNewZjId(room.getZj());
        }
        //②断开websocket
//        WebsocketService.exit(userId);
        //告诉其他玩家我退出了游戏
        if(isYczz){
            //异常中断,非正常断开退出
            wsMessageVo.setTishiMsg(userId+"号玩家非正常退出游戏(可能出现网络问题..若玩家无网络问题,请提醒他下次点击'退出房间'按钮退出)");
        }else{
            wsMessageVo.setTishiMsg(userId+"号玩家退出游戏了");
        }
        wsMessageVo.setMsgType("4");
        wsMessageVo.setUserId(userId);
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo),room.getOtherIds(userId));
    }
    public int getKeyByValue(List<String> list,String value){
        for (int i = 0; i < list.size(); i++) {
            if(value.equals(list.get(i))){
                return i;
            }
        }
        return 0;
    }

    @Override
    public String tanpai(String userId) {
        RoomDo room = getRoomByUserId(userId);
        Map<String, String> pai = room.getPai();
        List<String> tanpaiPeople = room.getTanpaiPeople();
        List<String> readyUserId = room.getReadyUserId();
        //封装自己摆的信息 发送给其他玩家
            //显示的结果
            String result = "";
            String value = pai.get(userId);
            int[] ints = suanPai(value);
            if(ints[0]==3){
                result = "炸弹";
            }else if(ints[0]==2){
                result = "五花";
            } else if(ints[0]==1){
                if (ints[1]==0){
                    result="牛牛";
                }else{
                    result = "牛"+ints[1];
                }
            }else {
                result="冒的牛";
            }
            WsMessageVo wsMessageVo = new WsMessageVo();
            wsMessageVo.setResult(result);
            wsMessageVo.setUserId(userId);
            wsMessageVo.setPk5(value);
            wsMessageVo.setMsgType("5");
            //推送个房间其他玩家
            WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo),room.getOtherIds(userId));
            if(!tanpaiPeople.contains(userId)){
                tanpaiPeople.add(userId);
            }
            if(tanpaiPeople.size()==readyUserId.size()){
                //都摊牌了,开始计算结果
                result(room);

            }

        return result;
    }

    @Override
    public void result(RoomDo room) {
        List<String> roomPeople = room.getRoomPeople();
        List<String> readyUserId = room.getReadyUserId();
        List<String> tanpaiPeople = room.getTanpaiPeople();
        String zj = room.getZj();
        Map<String, String> pai = room.getPai();
        Map<String, String> cashs = room.getCashs();
        //定义集合存放本回合输赢
        Map<String, Integer> shuyinResult = new HashMap<>();
        shuyinResult.put(zj,0);
        //获取庄家结果
        String pk1 = pai.get(zj);
        int[] zjPk = suanPai(pk1);
        //获取闲家牌并比较大小
        Set<Map.Entry<String, String>> entries = pai.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String userId = entry.getKey();
            if(!userId.equals(zj)){
                String value = entry.getValue();
                int[] pk2 = suanPai(value);
                int[] compare = compare(zjPk, pk2);
                //获取闲家本局押金
                Integer yj = Integer.valueOf(cashs.get(userId));
                if(compare[0]==1){
                    //庄赢
                    Integer integer = shuyinResult.get(zj);
                    Integer result = integer+(yj*compare[1]);
                    shuyinResult.put(zj, result);
                    shuyinResult.put(userId,0-(yj*compare[1]));
                }else{
                    //闲赢
                    Integer zjMny = shuyinResult.get(zj);
                    shuyinResult.put(zj, zjMny-(yj*compare[1]));
                    shuyinResult.put(userId,(yj*compare[1]));
                }
            }
        }

        //持久化结果
        Set<Map.Entry<String, Integer>> syResult = shuyinResult.entrySet();
        for (Map.Entry<String, Integer> stringIntegerEntry : syResult) {
            String userId = stringIntegerEntry.getKey();
            Integer money = stringIntegerEntry.getValue();
            userDao.addMoney(userId,money);

        }
        //推送结算面板信息
        WsMessageVo wsMessageVo = new WsMessageVo();
        wsMessageVo.setMsgType("6");
        wsMessageVo.setJiesuanMsg(shuyinResult);
        wsMessageVo.setNewZjId(zj);
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo),room.getRoomPeople());
        //如果庄家没牛就划庄
        if(zjPk[0]==0){
            for (int i = 0; i < roomPeople.size(); i++) {
                String aLong = roomPeople.get(i);
                if(aLong.equals(zj)){
                    if(i==(roomPeople.size()-1)){
                        room.setZj(roomPeople.get(0));
                        break;
                    }else{
                        room.setZj(roomPeople.get((i+1)));
                        break;
                    }
                }
            }
        }
        //清空本局信息
        readyUserId.clear();
        tanpaiPeople.clear();
        cashs.clear();
        pai.clear();
        //推送结算后的玩家信息
        WsMessageVo wsMessageVo2 = new WsMessageVo();
        List<WsMessageVo> allInfoByRoom = getAllInfoByRoom(room);
        wsMessageVo2.setAllInfo(allInfoByRoom);
        wsMessageVo2.setMsgType("7");
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo2),room.getRoomPeople());
    }

    @Override
    public R roomstuts(String roomId) {
        RoomDo room = getRoomByRoomId(roomId);
        R r = new R();
        if(room==null){
            if(room==null){
                r.put("num","0");
                r.put("status","0");
            }
            return r;
        }
        List<String> roomPeople = room.getRoomPeople();
        List<String> readyUserId = room.getReadyUserId();
        r.put("num",roomPeople.size()+"");
        if(roomPeople.size()==readyUserId.size()&&roomPeople.size()>1){
            r.put("status","1");
        }else {
            r.put("status","0");
        }
        return r;
    }

    /**
     * 清空积分
     */
    @Override
    public void clearScore() {
        int i = userDao.clearScore();
        System.out.println(i);
        //清空后提示所有玩家更新面板积分
        WsMessageVo wsMessageVo = new WsMessageVo();
        wsMessageVo.setMsgType("8");//8,积分清空消息
        WebsocketService.sendInfo(JSONObject.toJSONString(wsMessageVo));
    }
    /**
     * 清空指定房间所有玩家积分
     * */
    @Override
    public void clearScore(String roomId) {
        for (RoomDo room : rooms) {
            if(room.getRoomId().equals(roomId)){
                List<String> roomPeople = room.getRoomPeople();
                int i = userDao.clearScore2(roomPeople);
                System.out.println(i);
                //清空后提示所有玩家更新面板积分
                WsMessageVo wsMessageVo = new WsMessageVo();
                wsMessageVo.setMsgType("8");//8,积分清空消息
                WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo),room.getRoomPeople());
            }
        }
    }
    /**
     * 踢出玩家
     * @param userId
     */
    @Override
    public void tcyx(String userId) {
        RoomDo room = getRoomByUserId(userId);
        WsMessageVo wsMessageVo = getMsgByUserId(userId);
        wsMessageVo.setMsgType("9");
        WebsocketService.sendInfoToPeoples(JSONObject.toJSONString(wsMessageVo),room.getRoomPeople());
    }



    /**
     * 比较2副牌大小和番数(第一位数:-1表示小,1表示大,第二位数:番数)
     * @param pk1
     * @param pk2
     * @return
     */
    public int[] compare(int[] pk1,int[] pk2){
        //先计算是否翻倍
        int[] result = new  int[2];
        if(pk1[0]>pk2[0]){
            result[0]=1;
            result[1]=multiple(pk1);
        }else if (pk1[0]<pk2[0]){
            result[0]=-1;
            result[1]=multiple(pk2);
        }else if (pk1[0]==pk2[0]){
            int multiple1 = multiple(pk1);
            int multiple2 = multiple(pk2);
            if (multiple1>multiple2){
                result[0]=1;
                result[1]=multiple1;
            }else if(multiple1<multiple2){
                result[0]=-1;
                result[1]=multiple2;
            }else {
                //倍数相等,判断是否有牛
                if(pk1[0]==1){
                    //有牛,比较牛数
                    if(pk1[1]>pk2[1]){
                        result[0]=1;
                        result[1]=multiple1;
                    }else if(pk1[1]<pk2[1]){
                        result[0]=-1;
                        result[1]=multiple2;
                    }else {
                        //牛数相同,比较最大牌
                        if(pk1[2]>pk2[2]){
                            result[0]=1;
                            result[1]=multiple1;
                        }else{
                            result[0]=-1;
                            result[1]=multiple2;
                        }
                    }
                }else {
                    //无牛,比较最大牌
                    if(pk1[2]>pk2[2]){
                        result[0]=1;
                        result[1]=multiple1;
                    }else{
                        result[0]=-1;
                        result[1]=multiple2;
                    }
                }

            }

        }
        return  result;
    }
    public int multiple(int[] pk){
        if(pk[0]==1&&pk[1]==8){
            //牛8
            return 2;
        }else if (pk[0]==1&&pk[1]==9){
            //牛9
            return 3;
        }else if (pk[0]==1&&pk[1]==0){
            //牛牛
            return 4;
        }else if (pk[0]==2){
            //五花
            return 5;
        }else if (pk[0]==3){
            //炸弹
            return 6;
        }
        return 1;
    }

    /**通过userId 获取用户信息并封装成房间消息*/
    public WsMessageVo getMsgByUserId(String userId){
        RoomDo room = getRoomByUserId(userId);
        String zj = room.getZj();
        WsMessageVo wsMessageVo = new WsMessageVo();
        int score = userDao.queryScoreByUsrId(Integer.valueOf(userId));
        wsMessageVo.setScore(String.valueOf(score));
        UserDO userDO = userDao.get(Long.valueOf(userId));
        wsMessageVo.setUserName(userDO.getName());
        wsMessageVo.setUserId(userId);
        if(userId.equals(zj)){
            wsMessageVo.setIsZj("1");
        }
        return wsMessageVo;
    }

    public List<WsMessageVo> getAllInfoByRoom(RoomDo room){
        List<String> roomPeople = room.getRoomPeople();
        String zj = room.getZj();
        List<String> readyUserId = room.getReadyUserId();
        Map<String, String> cashs = room.getCashs();
        ArrayList<WsMessageVo> allInfo = new ArrayList<>();
        for (String sid : roomPeople) {
            UserDO userDO = userDao.get(Long.valueOf(sid));
            int score = userDao.queryScoreByUsrId(Integer.valueOf(sid));
            WsMessageVo info = new WsMessageVo();
            info.setUserId(userDO.getUserId().toString());
            info.setUserName(userDO.getName());
            info.setScore(String.valueOf(score));
            if(sid.equals(zj)){
                info.setIsZj("1");
            }
            //判断是否准备
            if(readyUserId.contains(sid)){
                info.setIsReady("1");
                info.setCash(cashs.get(sid));
            }
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
