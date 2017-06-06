package com.platform.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.entity.Goods;

public interface GoodsDao {
	
	int reduceInventory(@Param("goodsid") long goodsid,@Param("purchaseDate") Date purchaseDate);
	
	Goods queryById(long goodsid);
	
	List<Goods> listAllGoods(@Param("offset") int offset,@Param("limit") int limit);
	
    void purchaseByProcedure(Map<String, Object> paramMap);
}
