package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;
import com.zhangongjx.message.Request;

public class SaveSourceRequest extends Request {
    private  int type;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
