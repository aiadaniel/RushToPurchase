package com.platform.dao;

import com.platform.entity.Order;

public interface OrderDao {

	//ÇÀ¹º³É¹¦
	int insertGoods(long goodsid,long phone);
	
	Order queryById(long goodsid,long phone);
}
