package com.fc.jwtdemo.exception.jwt;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException() {
        super("인증이 필요합니다.");
    }
}
