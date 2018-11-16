package com.zhangongjx.message.statistics.view;

public class StatisticsTransactionViewModel {
    private int browseCount;
    private double payAmount;
    private int payOrderCount;
    private  int orderCount;
    private int dwgCount;

    public StatisticsTransactionViewModel() {
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getDwgCount() {
        return dwgCount;
    }

    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public int getPayOrderCount() {
        return payOrderCount;
    }

    public void setPayOrderCount(int payOrderCount) {
        this.payOrderCount = payOrderCount;
    }

    public void setDwgCount(int dwgCount) {
        this.dwgCount = dwgCount;
    }
}
