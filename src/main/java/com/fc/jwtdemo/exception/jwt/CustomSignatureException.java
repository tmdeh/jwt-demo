package com.fc.jwtdemo.exception.jwt;

import java.security.SignatureException;

public class CustomSignatureException extends SignatureException {
    CustomSignatureException() {
        super();
    }
}
