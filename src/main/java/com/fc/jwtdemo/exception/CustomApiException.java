package com.fc.jwtdemo.exception;
import com.fc.jwtdemo.exception.code.ErrorCode;
import org.springframework.web.client.HttpStatusCodeException;

public class CustomApiException extends HttpStatusCodeException {

    private final ErrorCode errorCode;

    public CustomApiException(ErrorCode errorCode) {
        super(errorCode.getStatus());
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorCode.getErrorMessage();
    }

}
