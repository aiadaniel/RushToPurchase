package com.platform.dao;

import com.platform.entity.Order;

public interface OrderDao {

	//�����ɹ�
	int insertGoods(long goodsid,long phone);
	
	Order queryById(long goodsid,long phone);
}
