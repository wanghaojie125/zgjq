package com.zhangongjx.message.statistics.view;

import com.zhangongjx.result.StatisticsMemberForOrderEntity;

import java.util.List;

public class StatisticsMemberOrderViewModel {
    private int total_member;
    private List<StatisticsMemberForOrderEntity> members;

    public StatisticsMemberOrderViewModel() {
    }

    public int getTotal_member() {
        return total_member;
    }

    public void setTotal_member(int total_member) {
        this.total_member = total_member;
    }

    public List<StatisticsMemberForOrderEntity> getMembers() {
        return members;
    }

    public void setMembers(List<StatisticsMemberForOrderEntity> members) {
        this.members = members;
    }
}
