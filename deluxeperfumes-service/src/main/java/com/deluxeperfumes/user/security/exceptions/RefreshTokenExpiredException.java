package com.deluxeperfumes.user.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RefreshTokenExpiredException extends AuthenticationException {

    public RefreshTokenExpiredException(String t) {
        super(t);
    }

}