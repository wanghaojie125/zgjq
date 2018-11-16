package com.zhangongjx.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthLockedErrorException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public AuthLockedErrorException(String msg) {
        super(msg);
    }

    public AuthLockedErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
