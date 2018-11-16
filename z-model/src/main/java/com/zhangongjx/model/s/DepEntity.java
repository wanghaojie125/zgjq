package com.zhangongjx.model.s;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

/*
 *部门表
 * */
public class DepEntity extends BaseEntity {
    private int enabled;
    private String name;
    private String desp;
    private int user_count;

    public  DepEntity()
    {

    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }
}
