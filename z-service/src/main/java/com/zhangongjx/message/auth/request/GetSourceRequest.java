package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;

public class GetSourceRequest extends Request {
    private  int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
