package com.bootdo.service.niuniu;

import com.bootdo.service.BaseTest;
import com.bootdo.websocket.sevice.impl.NiuniuServiceImpl;
import org.junit.Test;

import javax.annotation.Resource;

public class NiuNiuTest extends BaseTest {
    @Resource
    private NiuniuServiceImpl niuniuServiceImpl;
    @Test
    public void test1(){
        String s1= "47|11|14|35|19|";
        String s2= "12|16|33|51|34|";

        int[] ints1 = niuniuServiceImpl.suanPai(s1);
        int[] ints2 = niuniuServiceImpl.suanPai(s2);
        int[] compare = niuniuServiceImpl.compare(ints1, ints2);
        System.out.println();
    }
}
