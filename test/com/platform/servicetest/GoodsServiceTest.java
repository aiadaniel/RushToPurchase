package com.platform.servicetest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platform.dto.Exposer;
import com.platform.dto.PurchaseExec;
import com.platform.entity.Goods;
import com.platform.execption.PurchaseCloseException;
import com.platform.execption.RepeatPurchaseException;
import com.platform.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/spring-mybatis.xml"})
public class GoodsServiceTest {

	@Autowired
	GoodsService goodsService;
	
	@Test
	public void testListGoods() {
		List<Goods> goods = goodsService.getGoodsList();
		System.out.println("=="+goods);
	}
	
	@Test
	public void testExportUrl() {
		long goodsid = 1;
		Exposer exposer = goodsService.exportGoodsUrl(goodsid);
		System.out.println("==" + exposer);
	}
	
	@Test
	public void testPurchase() {
		long goodsid = 1;
		Exposer exposer = goodsService.exportGoodsUrl(goodsid);
		System.out.println("==" + exposer);
		long phone = 12333335556L;
		try {
			PurchaseExec purchaseExec = goodsService.executePurchase(goodsid, phone, exposer.getMd5());
			System.out.println("=="+purchaseExec);
		} catch (RepeatPurchaseException e1) {
			e1.printStackTrace();
		} catch (PurchaseCloseException e2) {
			e2.printStackTrace();
		}
		
	}

}
