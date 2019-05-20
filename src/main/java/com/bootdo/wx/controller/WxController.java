package com.bootdo.wx.controller;

import com.bootdo.common.aspect.WebLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.component.MessageComponent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-05-19 14:54
 **/
@Controller
@RequestMapping("/wxServer2")
public class WxController {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    private final  static String token = "yff";
    @ResponseBody
    @GetMapping
    public String checkSignature(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //排序
        String[] params = {token, timestamp, nonce};
        Arrays.sort(params);
        //拼接
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i]);
        }
        //加密
        String sha1 = getSha1(stringBuilder.toString());
        if(sha1.equals(signature)) {
            return echostr;
        }
        return "erro";
    }
    @PostMapping
    public void server(HttpServletRequest request, HttpServletResponse response){

    }

    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
