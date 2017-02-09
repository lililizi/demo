package org.lizi.demo.enums;

/**
 * 使用枚举表示常量数据字典
 * @Author: 力子
 * @Description:
 * @Date: Created in 3:10 2016/10/29.
 */
public enum GetStatEnum {
    SUCCESS(1,"抢红包成功"),
    END(0,"抢红包结束"),
    REPEAT_KILL(-1,"重复了"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");
    private int state;
    private String stateinfo;

    public int getState() {
        return state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    GetStatEnum(int state, String stateinfo) {

        this.state = state;
        this.stateinfo = stateinfo;
    }
    public static GetStatEnum stateof(int index){
        for (GetStatEnum s:values()) {
            if(s.getState()==index)
                return s;
        }
        return null;
    }
}
