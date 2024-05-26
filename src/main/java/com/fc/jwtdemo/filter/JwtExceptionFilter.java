package com.fc.jwtdemo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.jwtdemo.exception.CustomApiException;
import com.fc.jwtdemo.exception.code.AuthErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex){
            handleException(response, new CustomApiException(AuthErrorCode.EXPIRED_TOKEN));
        } catch (SignatureException ex) {
            handleException(response, new CustomApiException(AuthErrorCode.INVALID_TOKEN));
        } catch (AuthenticationException ex) {
            handleException(response, new CustomApiException(AuthErrorCode.UNAUTHORIZED));
        }
    }

    private void handleException(HttpServletResponse response, CustomApiException ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=UTF-8");
//        String responseBody = objectMapper.writeValueAsString(new ErrorResponse(ex.getErrorMessage()));
//        response.getWriter().write(responseBody);
    }
}
