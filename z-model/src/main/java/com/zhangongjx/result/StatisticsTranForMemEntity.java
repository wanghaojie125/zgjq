package com.zhangongjx.result;

public class StatisticsTranForMemEntity {
    private int count;
    private int m_count;
    private  String months;
    private String m_months;
    private  double amount;

    public StatisticsTranForMemEntity() {

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

    public int getM_count() {
        return m_count;
    }

    public void setM_count(int m_count) {
        this.m_count = m_count;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getM_months() {
        return m_months;
    }

    public void setM_months(String m_months) {
        this.m_months = m_months;
    }
}
