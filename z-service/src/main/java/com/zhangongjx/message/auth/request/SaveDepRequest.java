package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;

public class SaveDepRequest extends Request {
    private  String name;
    private  String desp;

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
}
