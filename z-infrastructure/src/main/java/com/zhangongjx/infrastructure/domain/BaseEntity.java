package com.zhangongjx.infrastructure.domain;

import java.io.Serializable;
/*
*基础模型
* */
public class BaseEntity implements Serializable {
    private int id;
    private String create_at;
    private String update_at;
    private boolean deleted;

    public  BaseEntity()
    {

    }
    public BaseEntity(int id, String create_at, String update_at, boolean deleted) {
        this.id = id;
        this.create_at = create_at;
        this.update_at = update_at;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
