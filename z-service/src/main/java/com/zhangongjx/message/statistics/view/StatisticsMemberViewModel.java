package com.zhangongjx.message.statistics.view;

public class StatisticsMemberViewModel {
    private int orderCount;
    private double orderAmount;
    private int loginCount;
    private int dwgCount;

    public StatisticsMemberViewModel() {
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public int getDwgCount() {
        return dwgCount;
    }

    public void setDwgCount(int dwgCount) {
        this.dwgCount = dwgCount;
    }
}
