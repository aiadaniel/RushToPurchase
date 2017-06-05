package com.platform.entity;

import java.util.Date;
/**
 * 订单实体类
 */
public class Order {
	private long goodsId;
    private long userPhone;
    private short state;
	private Date createTime;
	
	private Goods purchase;//库存多个

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Goods getPurchase() {
		return purchase;
	}

	public void setPurchase(Goods purchase) {
		this.purchase = purchase;
	}
}
