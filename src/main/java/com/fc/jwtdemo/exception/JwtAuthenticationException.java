package com.fc.jwtdemo.exception;

import com.fc.jwtdemo.exception.code.AuthErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtAuthenticationException extends RuntimeException{

    private final AuthErrorCode errorCode;
    private final HttpStatus httpStatus;

    public JwtAuthenticationException(AuthErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getStatus();
    }



}
