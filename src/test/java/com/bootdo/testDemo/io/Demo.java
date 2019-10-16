package com.bootdo.testDemo.io;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;
import java.util.UUID;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-09-11 14:21
 **/
public class Demo {
    //io总结
    //①从处理数据类型分为字符流和字节流,从数据流向来分为输入流和输出流
    //②输入流:inputStream,Reader,做reader操作来读取流中的数据
    //③输出流:outputStream.Writer,做writer操作来写要输出的内容
    //④桥梁流:inputStreamReader,OutputStreamWriter,用来做字符流和字节流之间得转换,它们本身是字符流
    public static void main(String[] args) throws Exception {
        //本地文件读取和写入
       /* File file = new File("C:\\Users\\Lenovo\\Desktop\\导入测试文件","10291528-参保信息-201905.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[10000000];
        int read = bufferedInputStream.read(bytes);
        System.out.println(read);
        fileInputStream.close();
        File path = new File("C:\\Users\\Lenovo\\Desktop\\yffff");
        if(!path.exists()){
            path.mkdirs();
        }
        for (int i = 0; i < 10; i++) {
            File file1 = new File(path, UUID.randomUUID()+".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }*/
        //清除测试数据
        File file = new File("C:\\Users\\Lenovo\\Desktop","清除测试数据.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"GBK"));
        String str ;
        File file2 = new File("C:\\Users\\Lenovo\\Desktop\\yffff","yffn.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        while ((str = bufferedReader.readLine())!=null){
            System.out.println(str);
            bufferedWriter.write(str+"niubi");
//            bufferedWriter.newLine();
        }
//        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
