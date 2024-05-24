package com.fc.jwtdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class Response {
    private HttpStatusCode statusCode;
    private String message;
}
