package com.platform.dao;

import java.util.Date;
import java.util.List;

import com.platform.entity.Goods;

public interface GoodsDao {
	
	//�����ɹ�
	int reduceInventory(long goodsid,Date purchaseDate);
	
	Goods queryById(long goodsid);
	
	List<Goods> listAllGoods(int offset,int limit);
}
