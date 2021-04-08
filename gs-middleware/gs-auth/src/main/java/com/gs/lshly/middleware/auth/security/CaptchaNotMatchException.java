package com.gs.lshly.middleware.auth.security;

import org.springframework.security.core.AuthenticationException;

public class CaptchaNotMatchException extends AuthenticationException {
    public CaptchaNotMatchException(String msg) {
        super(msg);
    }
}
