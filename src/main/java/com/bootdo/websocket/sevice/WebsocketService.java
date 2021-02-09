package com.bootdo.websocket.sevice;

import com.bootdo.websocket.sevice.impl.NiuniuServiceImpl;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebsocketService {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebsocketService> webSocketSet = new CopyOnWriteArraySet<WebsocketService>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";
    /**
     * 获取所有的sid
     */
    public List<String> getAllSid(){
        List<String> sids = new ArrayList<>();
        for (WebsocketService item : webSocketSet) {
            sids.add(item.sid);
        }
        return sids;
    }
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        WebsocketService rmitem = null;
        for (WebsocketService item : webSocketSet) {
            //如果相同sid多次连接 则删除之前的连接
            if(sid.equals(item.sid)){
                rmitem = item;
            }
        }
        if(rmitem!=null){
            webSocketSet.remove(rmitem);
            subOnlineCount();
        }
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
//        RoomDo room = NiuniuServiceImpl.getRoomByUserId(sid);
//        room.getRoomPeople().remove(this.sid);
//        room.getCashs().remove(sid);
//        room.getReadyUserId().remove(sid);
//        room.getPai().remove(sid);
//        room.getTanpaiPeople().remove(sid);
        webSocketSet.remove(this);  //从set中删除
        //告诉其他玩家我以退出
        NiuniuServiceImpl niuniuService = new NiuniuServiceImpl();
        niuniuService.exit(sid,true);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (WebsocketService item : webSocketSet) {

                item.sendMessage(sid+"说:"+message);

        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送，这里可能会出现并发报错，在方法上加 synchronized 就可以了
     */
    public void sendMessage(String message)  {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid)  {
        System.out.println("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebsocketService item : webSocketSet) {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }

        }
    }
    /**
     * 发送消息给其他窗口,不发送自己
     * */
    public static void sendOtherInfo(String message,@PathParam("sid") String sid)  {
        for (WebsocketService item : webSocketSet) {
            //这里可以设定只推送给这个sid的，为null则全部推送
            if(sid==null) {
                item.sendMessage(message);
            }else if(!item.sid.equals(sid)){
                item.sendMessage(message);
            }

        }
    }
    /**
     * 发送消息给其他窗口,不发送自己
     * */
    public static void sendInfoToPeoples(String message,List<String> userIds)  {

        for (WebsocketService item : webSocketSet) {
            for (String userId : userIds) {
                if(item.sid.equals(userId)) {
                    item.sendMessage(message);
                }
            }
        }
    }
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) {
        for (WebsocketService item : webSocketSet) {
                item.sendMessage(message);
        }
    }
    /**断开连接*/
    public static synchronized void exit(String sid){
        for (Iterator<WebsocketService> iterator = webSocketSet.iterator();iterator.hasNext();) {
            WebsocketService item = iterator.next();
            if(item.sid.equals(sid)){
                item.onClose();
            }
        }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketService.onlineCount--;
    }
}
