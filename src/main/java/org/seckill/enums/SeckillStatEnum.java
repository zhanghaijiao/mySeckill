package org.seckill.enums;

/**
 * 使用枚举表述常量数据字段
 * Created by zhj on 2017-7-5.
 */
public enum SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀技术"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNERT_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");



    private int state;
    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum s:values()){
            if(s.getState()==index){
                return  s;
            }
        }
        return  null;
    }
}