package com.fc.jwtdemo.exception;
import com.fc.jwtdemo.exception.code.ErrorCode;
import lombok.Getter;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class CustomApiException extends HttpStatusCodeException {

    private final ErrorCode errorCode;

    public CustomApiException(ErrorCode errorCode) {
        super(errorCode.getStatus());
        this.errorCode = errorCode;
    }
}
