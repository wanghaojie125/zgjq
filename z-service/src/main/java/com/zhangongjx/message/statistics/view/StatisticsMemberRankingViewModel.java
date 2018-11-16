package com.zhangongjx.message.statistics.view;

import com.zhangongjx.result.StatisticsMemberForOrderEntity;

import java.util.List;

public class StatisticsMemberRankingViewModel {
    private int count;
    private double amount;
    private String user_name;

    public StatisticsMemberRankingViewModel() {
    }

    public StatisticsMemberRankingViewModel(int count, double amount, String user_name) {
        this.count = count;
        this.amount = amount;
        this.user_name = user_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
