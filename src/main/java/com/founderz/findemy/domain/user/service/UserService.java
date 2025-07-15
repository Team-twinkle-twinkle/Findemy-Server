package com.founderz.findemy.domain.user.service;

import com.founderz.findemy.domain.auth.dto.AuthElementDto;
import com.founderz.findemy.domain.auth.dto.response.TokenResponse;
import com.founderz.findemy.domain.user.controller.dto.request.AuthRequest;
import com.founderz.findemy.domain.user.entity.User;
import com.founderz.findemy.domain.user.repository.UserRepository;
import com.founderz.findemy.global.exception.domain.student.UserNotFoundException;
import com.founderz.findemy.global.exception.domain.user.PasswordMismatchException;
import com.founderz.findemy.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(AuthRequest request) {
        userRepository.save(
                User.builder()
                        .accountId(request.account_id())
                        .password(passwordEncoder.encode(request.password()))
                        .build());
    }

    @Transactional
    public TokenResponse login(AuthRequest request) {
        User user = userRepository.findByAccountId(request.account_id())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.password(), user.getPassword())) throw PasswordMismatchException.EXCEPTION;

        return jwtTokenProvider.receiveToken(request.account_id(), AuthElementDto.UserRole.USER);
    }
}
