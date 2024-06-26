package com.fc.jwtdemo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.jwtdemo.config.security.jwt.JwtUtil;
import com.fc.jwtdemo.exception.CustomApiException;
import com.fc.jwtdemo.exception.code.AuthErrorCode;
import com.fc.jwtdemo.model.dto.CustomUserDetails;
import com.fc.jwtdemo.model.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

//    private final static Long EXPIRED_AT = 24 * 60 * 60 * 1000L;
    private final static Long ACCESS_EXPIRED_AT = 0L;
    private final static Long REFRESH_EXPIRED_AT = 0L;


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {

        if(!request.getContentType().equals("application/json")) {
            throw new CustomApiException(AuthErrorCode.APPLICATION_TYPE_ERROR);
        }

        try {
        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword(),
            null
        );
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            log.error("Error reading email and password from request", e);
            throw new CustomApiException(AuthErrorCode.INVALID_INPUT);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {

        //인증된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        //JWT 토큰 발급 및 쿠키에 등록
        String accessToken = jwtUtil.createJwt(userDetails.getUser().getId(), ACCESS_EXPIRED_AT);
        String refreshToken = jwtUtil.createJwt(REFRESH_EXPIRED_AT);

        response.addHeader("Authorization", "Bearer " + accessToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Authentication successful\"}");

    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed: " + failed.getMessage());
    }
}