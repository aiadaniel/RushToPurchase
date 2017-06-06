package com.platform.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platform.entity.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/spring-mybatis.xml"})
/*
 * NOTE: spring-mybatis.xml中的jdbc-dev.properties在单元测试时要加file:WebContent，但是生产环境加了会找不到路径。。。。。。
 */
public class GoodsDaoTest {
	
	@Resource
	GoodsDao goodsDao;

	@Test
	public void testReduceInventory() {
		long goodsid = 1;
		Date date = new Date();
		int update = goodsDao.reduceInventory(goodsid, date);
		System.out.println("==" + update);
	}
	
	@Test
	public void testQueryById() {
		long goodsid = 1;
		Goods goods = goodsDao.queryById(goodsid);
		System.out.println("==get goods " + goods.getGoodsName());
	}

	@Test
	public void listAll() {
		List<Goods> goods = goodsDao.listAllGoods(0, Integer.MAX_VALUE);
		for (Goods g:goods) {
			System.out.println("==goods item " + g.getGoodsName());
		}
	}
}
