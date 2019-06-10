package com.platform.dao;

import com.platform.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:/WEB-INF/spring-mybatis.xml"})
public class OrderDaoTest {

    @Resource
    OrderDao orderDao;

    @Test
    public void testInsert() {
        long goodsid = 1;
        long phone = 12333334444L;
        int update = orderDao.insertGoods(goodsid, phone);
        System.out.println("== " + update);
    }

    @Test
    public void testQueryById() {
        long goodsid = 1;
        long phone = 12333334444L;
        Order order = orderDao.queryById(goodsid, phone);
        System.out.println("==" + order.getGoodsId() + " " + order.getUserPhone());
        System.out.println("==" + order.getGoods().getGoodsName());
    }
}
