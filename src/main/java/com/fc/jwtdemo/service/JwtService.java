package com.fc.jwtdemo.service;

import com.fc.jwtdemo.jwt.JwtUtil;
import com.fc.jwtdemo.model.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;


    public void createAndAddHeader(CustomUserDetails userDetails, HttpServletResponse response) {
        // JWT 토큰 발급
        String token = jwtUtil.createJwt(userDetails.getUser().getId(), 24 * 3600 * 1000L);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
