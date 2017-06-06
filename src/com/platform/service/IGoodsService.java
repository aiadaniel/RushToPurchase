package com.platform.service;

import java.util.List;

import com.platform.dto.Exposer;
import com.platform.dto.PurchaseExec;
import com.platform.entity.Goods;
import com.platform.execption.RepeatPurchaseException;
import com.platform.execption.PurchaseCloseException;
import com.platform.execption.RushPurchaseException;


public interface IGoodsService {

    List<Goods> getGoodsList();

    Goods getById(long goodsid);

    Exposer exportGoodsUrl(long goodsid);

    PurchaseExec executePurchase(long goodsid, long userPhone, String md5) throws RushPurchaseException
            , RepeatPurchaseException, PurchaseCloseException;

    //ʹ�ô洢����
    PurchaseExec executePurchaseProcedure(long goodsid, long userPhone, String md5);

}
