package com.zhangongjx.model.c;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class ConfVerifyCodeEntity extends BaseEntity {
    private int register;
    private int login;
    private int sys_login;
    private int login_fail;
    private String way;
    private int width;
    private int height;
    private int size;
    private int digit;

    public ConfVerifyCodeEntity() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getSys_login() {
        return sys_login;
    }

    public void setSys_login(int sys_login) {
        this.sys_login = sys_login;
    }

    public int getLogin_fail() {
        return login_fail;
    }

    public void setLogin_fail(int login_fail) {
        this.login_fail = login_fail;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }
}
