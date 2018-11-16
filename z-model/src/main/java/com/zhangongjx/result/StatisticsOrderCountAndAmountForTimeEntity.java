package com.zhangongjx.result;

public class StatisticsOrderCountAndAmountForTimeEntity {
    private int count;
    private String date;
    private double amount;

    public StatisticsOrderCountAndAmountForTimeEntity() {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
