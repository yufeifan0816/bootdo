package com.bootdo.testDemo.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-11 10:31
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",1314);
        System.out.println("连接成功");
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("发送消息....");

    }
}
