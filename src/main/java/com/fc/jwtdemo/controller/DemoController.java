package com.fc.jwtdemo.controller;

import com.fc.jwtdemo.model.request.SignUpRequest;
import com.fc.jwtdemo.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final CustomUserDetailService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(
        @RequestBody SignUpRequest signUpRequest
    ) {
        userService.signUp(signUpRequest);
        return ResponseEntity.ok("Sign up successful");
    }


    @GetMapping
    public String test() {
        return "성공";
    }
}
