package com.bootdo.service.xjbg;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.Query;
import com.bootdo.service.BaseTest;
import com.bootdo.xjbg.service.OrderService;
import com.bootdo.xjbg.vo.OrderVo;
import com.github.pagehelper.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

//@Transactional
public class OrderServiceTest extends BaseTest {
    @Autowired
    private OrderService orderService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() {
    }

    @Test
    public void list() {
    }

    @Test
    public void count() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void batchRemove() {
    }

    @Test
    public void addOrder() {
    }

    @Test
    public void findByRoomId() {
    }

    @Test
    public void updateOrder() {
    }
    @Test
    public void orderList() {
        List<OrderVo> orderVos = orderService.orderList(null);
        com.alibaba.druid.support.json.JSONUtils.toJSONString(orderVos);
    }
    @Test
    public void checkOut() throws Exception {
        orderService.checkOut(12, "1", "1");
    }
    /**分页插件测试*/
    @Test
    public void pageTest() throws Exception {
        HashMap<Object, Object> param = new HashMap<>();
        Page<OrderVo> page = orderService.pageQuery(2,5,param);
        System.out.println(page.size());
        System.out.println("当前页:"+page.getPageNum());
        System.out.println("总共条数:"+page.getTotal());
        System.out.println("没页条数:"+page.getPageSize());
        System.out.println(JSON.toJSONString(page));
    }
}