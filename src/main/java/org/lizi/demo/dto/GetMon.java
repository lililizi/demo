package org.lizi.demo.dto;

import org.lizi.demo.domain.SuccessGet;
import org.lizi.demo.enums.GetStatEnum;

/**
 * Created by touch on 2017/1/19.
 */
public class GetMon {
    private long rId;
    private float money;
    private int state;
    private String stateInfo;
    private SuccessGet successGet;

    public GetMon(long rId, GetStatEnum stateInfo) {
        this.rId = rId;
        this.stateInfo = stateInfo.getStateinfo();
        this.state=stateInfo.getState();
    }


    public GetMon(long rId, GetStatEnum stateInfo, SuccessGet successGet) {
        this.rId = rId;
        this.stateInfo = stateInfo.getStateinfo();
        this.successGet = successGet;
    }

    public GetMon(long rId,  int state, GetStatEnum stateInfo,SuccessGet successGet) {
        this.rId = rId;
        this.state = stateInfo.getState();
        this.stateInfo = stateInfo.getStateinfo();
        this.successGet = successGet;
    }


    public long getrId() {
        return rId;
    }

    public void setrId(long rId) {
        this.rId = rId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

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

    public GetMon(long rId, float money, String stateInfo, SuccessGet successGet) {
        this.rId = rId;
        this.money = money;
        this.stateInfo = stateInfo;
        this.successGet = successGet;
    }

    public SuccessGet getSuccessGet() {

        return successGet;
    }

    public void setSuccessGet(SuccessGet successGet) {
        this.successGet = successGet;
    }
}
