package com.zhangongjx.auth;

import com.zhangongjx.exception.AuthPwdErrorException;
import com.zhangongjx.infrastructure.util.Base64Helper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Base64PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return Base64Helper.Base64(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String password) throws AuthPwdErrorException {
        String pwd = Base64Helper.Base64(charSequence);
        boolean res=password.equals(pwd);
        if(res==false)
        {
            throw  new AuthPwdErrorException("用户名或密码不正确");
        }
        return res;
    }
}
