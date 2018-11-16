package com.zhangongjx.result;

public class StatisticsOrderStatusEntity {
    private int count;
    private int status;
    private double amount;

    public StatisticsOrderStatusEntity() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
