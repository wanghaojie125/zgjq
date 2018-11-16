package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;
import com.zhangongjx.message.Request;

public class EnableUserRequest extends Request {
    private  int enabled;

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
