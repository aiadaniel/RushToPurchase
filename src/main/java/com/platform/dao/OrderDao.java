package com.platform.dao;

import com.platform.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {

    int insertGoods(@Param("goodsid") long goodsid, @Param("phone") long phone);

    Order queryById(@Param("goodsid") long goodsid, @Param("phone") long phone);
}
