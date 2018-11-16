package com.zhangongjx.result;

public class StatisticsMemberForOrderEntity {
    private int count;
    private double amount;
    private  int member_id;

    public StatisticsMemberForOrderEntity() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
