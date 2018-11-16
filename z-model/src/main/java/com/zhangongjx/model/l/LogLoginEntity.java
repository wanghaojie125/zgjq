package com.zhangongjx.model.l;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class LogLoginEntity extends BaseEntity {
    private String login_at;
    private String ip;
    private String area;
    private String browser;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public LogLoginEntity() {
    }

    public String getLogin_at() {
        return login_at;
    }

    public void setLogin_at(String login_at) {
        this.login_at = login_at;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
