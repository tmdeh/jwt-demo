package com.fc.jwtdemo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(JwtAuthenticationException.class)
    public void handleJwtAuthenticationException(JwtAuthenticationException ex, HttpServletResponse response) throws IOException {
        log.error("JWT Authentication error: {}", ex.getMessage());
        response.setStatus(ex.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getErrorCode().name());
        errorDetails.put("message", ex.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> exception(CustomApiException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 서버 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
