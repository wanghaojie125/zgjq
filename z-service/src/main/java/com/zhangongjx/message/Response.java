package com.zhangongjx.message;

import com.zhangongjx.infrastructure.domain.ResutlMsg;

public class Response {
    private ResutlMsg<Object> resultMsg;

    public ResutlMsg<Object> getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(ResutlMsg<Object> resultMsg) {
        this.resultMsg = resultMsg;
    }
}
