package com.zhangongjx.result;

public class StatisticsGoodsStatusEntity {
    private int count;
    private int status;
    private  int alarm_count;

    public StatisticsGoodsStatusEntity() {

    }

    public int getAlarm_count() {
        return alarm_count;
    }
    public void setAlarm_count(int alarm_count) {
        this.alarm_count = alarm_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
