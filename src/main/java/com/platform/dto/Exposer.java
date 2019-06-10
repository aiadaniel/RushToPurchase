package com.platform.dto;

import java.io.Serializable;

/**
 * 暴露抢购地址DTO(dto:web层和service层传递数据用)
 */
public class Exposer implements Serializable {

    private static final long serialVersionUID = 7602244494037452541L;

    private boolean exposed;

    private String md5;

    private long goodsid;

    private long now;

    private long start;

    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.goodsid = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.goodsid = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.goodsid = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(long goodsid) {
        this.goodsid = goodsid;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", goodsid=" + goodsid +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
