package com.platform.dto;

public enum PurchaseStatEnum {

    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_PURCHASE(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改"),
    NOT_LOGIN(-4, "未登陆");

    private int state;

    private String stateInfo;

    PurchaseStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static PurchaseStatEnum stateOf(int index) {
        for (PurchaseStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
