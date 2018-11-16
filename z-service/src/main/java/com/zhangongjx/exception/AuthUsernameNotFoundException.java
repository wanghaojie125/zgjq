package com.zhangongjx.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthUsernameNotFoundException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public AuthUsernameNotFoundException(String msg) {
        super(msg);
    }

    public AuthUsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
