package org.lizi.demo.domain;

import java.util.Date;

/**
 * Created by touch on 2017/1/18.
 */
public class RedPacket {
    private long rId;
    private String name;
    private Float balance;
    private Float total;
    private Date startTime;
    private Date endTime;

    @Override
    public String toString() {
        return "RedPacket{" +
                "rId=" + rId +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", total=" + total +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
