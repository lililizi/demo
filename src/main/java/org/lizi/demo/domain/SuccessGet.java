package org.lizi.demo.domain;

import java.util.Date;

/**
 * Created by touch on 2017/1/18.
 */
public class SuccessGet {
    private long rId;
    private long userPhone;
    private Float money;
    private Date createTime;
    private RedPacket redPacket;

    @Override
    public String toString() {
        return "SuccessGet{" +
                "rId=" + rId +
                ", userPhone=" + userPhone +
                ", money=" + money +
                ", createTime=" + createTime +
                ", redPacket=" + redPacket +
                '}';
    }

    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public RedPacket getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.redPacket = redPacket;
    }
}
