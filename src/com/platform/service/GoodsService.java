package com.platform.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.platform.dao.GoodsDao;
import com.platform.dao.OrderDao;
import com.platform.dto.Exposer;
import com.platform.dto.PurchaseExec;
import com.platform.dto.PurchaseStatEnum;
import com.platform.entity.Goods;
import com.platform.entity.Order;
import com.platform.execption.PurchaseCloseException;
import com.platform.execption.RepeatPurchaseException;
import com.platform.execption.RushPurchaseException;

@Service
public class GoodsService implements IGoodsService {

	private Log LOG = LogFactory.getLog(this.getClass());

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private OrderDao orderDao;

	private final String slat = "rtbnyiljfario67bxjieZbhz#%$ZZZA67";

	public List<Goods> getGoodsList() {
		return goodsDao.listAllGoods(0, 1000);
	}

	public Goods getById(long goodsid) {
		return goodsDao.queryById(goodsid);
	}

	public Exposer exportGoodsUrl(long goodsid) {

		Goods goods = null;

		if (goods == null) {
			goods = getById(goodsid);
			if (goods == null) {
				return new Exposer(false, goodsid);
			} else {

			}
		}

		Date startTime = goods.getStartTime();
		Date endTime = goods.getEndTime();

		Date nowTime = new Date();

		if (nowTime.getTime() > endTime.getTime() || nowTime.getTime() < startTime.getTime()) {
			return new Exposer(false, goodsid, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMD5(goodsid);
		return new Exposer(true, md5, goodsid);
	}

	@Transactional
	public PurchaseExec executePurchase(long goodsid, long userPhone, String md5)
			throws RushPurchaseException, RepeatPurchaseException, PurchaseCloseException {

		if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(goodsid))) {
			throw new RushPurchaseException(PurchaseStatEnum.DATA_REWRITE.getStateInfo());
		}

		Date nowTime = new Date();

		try {
			int inserCount = orderDao.insertGoods(goodsid, userPhone);
			if (inserCount <= 0) {
				throw new RepeatPurchaseException(PurchaseStatEnum.REPEAT_PURCHASE.getStateInfo());
			} else {
				int updateCount = goodsDao.reduceInventory(goodsid, nowTime);

				if (updateCount <= 0) {
					// rollback
					throw new PurchaseCloseException(PurchaseStatEnum.END.getStateInfo());
				} else {
					// commit
					Order order = orderDao.queryById(goodsid, userPhone);
					return new PurchaseExec(goodsid, PurchaseStatEnum.SUCCESS, order);
				}

			}

		} catch (PurchaseCloseException e1) {
			throw e1;
		} catch (RepeatPurchaseException e2) {
			throw e2;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			// 依赖spring事务rollback
			throw new RushPurchaseException("purchase inner error: " + e.getMessage());
		}

	}

	public PurchaseExec executePurchaseProcedure(long goodsid, long userPhone, String md5) {
		if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(goodsid))) {
			throw new RushPurchaseException(PurchaseStatEnum.DATA_REWRITE.getStateInfo());
		}

		Date purchaseTime = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsid", goodsid);
		map.put("phone", userPhone);
		map.put("purchaseTime", purchaseTime);
		map.put("result", null);
		try {
			goodsDao.purchaseByProcedure(map);
			//int result = MapUtils.getInteger(map, "result", -2);//deprecated
			int result = (map.get("result") == null ? -2 : (int) map.get("result"));
			if (result == 1) {
				Order sk = orderDao.queryById(goodsid, userPhone);
				return new PurchaseExec(goodsid, PurchaseStatEnum.SUCCESS, sk);
			} else {
				return new PurchaseExec(goodsid, PurchaseStatEnum.stateOf(result));
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new PurchaseExec(goodsid, PurchaseStatEnum.INNER_ERROR);
		}

	}

	private String getMD5(long goodsid) {
		String base = goodsid + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		LOG.info("==md5: " + md5);
		return md5;
	}
}
