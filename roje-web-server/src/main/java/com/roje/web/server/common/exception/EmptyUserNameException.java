package com.roje.web.server.common.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Ro
 */
public class EmptyUserNameException extends UsernameNotFoundException {
    private static final long serialVersionUID = -2562586171843672061L;

    public EmptyUserNameException(String msg) {
        super(msg);
    }
}
