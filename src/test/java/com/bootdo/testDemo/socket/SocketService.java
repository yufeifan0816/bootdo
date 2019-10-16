package com.bootdo.testDemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-11 10:30
 **/
public class SocketService {
    //启动socket服务
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1314);
        serverSocket.setSoTimeout(10000);
        System.out.println("等待连接...");
        Socket accept = serverSocket.accept();
        System.out.println("连接成功...");
//        OutputStream outputStream = accept.getOutputStream();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[100];
        int read = inputStream.read(bytes);
//        outputStream.write(bytes);
        System.out.println("服务端收到消息++++"+new String(bytes));
    }
}
