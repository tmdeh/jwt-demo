package com.fc.jwtdemo.service;

import com.fc.jwtdemo.exception.CustomApiException;
import com.fc.jwtdemo.exception.code.AuthErrorCode;
import com.fc.jwtdemo.model.dto.CustomUserDetails;
import com.fc.jwtdemo.model.entity.User;
import com.fc.jwtdemo.model.request.SignUpRequest;
import com.fc.jwtdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        ;
        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new CustomApiException(AuthErrorCode.NOT_FOUND)
        );
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomApiException(AuthErrorCode.NOT_FOUND));
        return new CustomUserDetails(user);
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .name(request.getName())
            .build();

        userRepository.save(user);
    }


}
