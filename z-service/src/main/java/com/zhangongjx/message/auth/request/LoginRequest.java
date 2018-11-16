package com.zhangongjx.message.auth.request;

import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.Request;

public class LoginRequest extends Request {
    private String userName;
    private String passWord;

    public LoginRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public ResutlMsg verify() {
        if (userName == null || userName.length() == 0) {
            return ResutlMsg.error("用户名或密码不能为空");
        }
        if (passWord == null || passWord.length() == 0) {
            return ResutlMsg.error("用户名或密码不能为空");
        }
        return super.verify();
    }
}
