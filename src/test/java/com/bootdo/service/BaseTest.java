package com.bootdo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-08-08 09:41
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseTest {

    @Test
    public void test(){
        System.out.println("测试测试=====================");
    }
}
