package com.fc.jwtdemo.exception.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class CustomExpiredJwtException extends ExpiredJwtException {

    public CustomExpiredJwtException(Header header, Claims claims) {
        super(header, claims, "토큰이 만료되었습니다.");
    }
}
