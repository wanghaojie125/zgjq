package com.zhangongjx.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthPwdErrorException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public AuthPwdErrorException(String msg) {
        super(msg);
    }

    public AuthPwdErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
