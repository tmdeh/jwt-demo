package com.fc.jwtdemo.exception.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getStatus();
    String getErrorMessage();

}