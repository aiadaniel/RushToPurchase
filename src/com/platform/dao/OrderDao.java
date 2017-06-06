package com.platform.dao;

import org.apache.ibatis.annotations.Param;

import com.platform.entity.Order;

public interface OrderDao {

	int insertGoods(@Param("goodsid")long goodsid,@Param("phone")long phone);
	
	Order queryById(@Param("goodsid")long goodsid,@Param("phone")long phone);
}
