package com.roje.web.server.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Ro
 */
public class CaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 9126425654097509357L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
