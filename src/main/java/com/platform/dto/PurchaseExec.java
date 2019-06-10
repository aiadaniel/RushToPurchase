package com.platform.dto;

import com.platform.entity.Order;

import java.io.Serializable;

public class PurchaseExec implements Serializable {

    private static final long serialVersionUID = 2160123709223365015L;

    private long goodsid;

    private int state;

    private String stateInfo;

    private Order order;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }


    public PurchaseExec(long goodsid, PurchaseStatEnum statEnum) {
        this.goodsid = goodsid;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public PurchaseExec(long goodsid, PurchaseStatEnum statEnum, String stateInfo) {
        this.goodsid = goodsid;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.stateInfo = stateInfo;
    }

    public PurchaseExec(long goodsid, PurchaseStatEnum statEnum, Order order) {
        this.goodsid = goodsid;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.order = order;
    }

    @Override
    public String toString() {
        return "PurchaseExec {" +
                "goodsid=" + goodsid +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", order=" + order +
                '}';
    }
}
