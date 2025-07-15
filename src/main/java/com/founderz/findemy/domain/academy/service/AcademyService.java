package com.founderz.findemy.domain.academy.service;

import com.founderz.findemy.domain.academy.controller.dto.request.AcademyRequest;
import com.founderz.findemy.domain.academy.controller.dto.response.AcademyResponse;
import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.academy.repository.AcademyRepository;
import com.founderz.findemy.domain.auth.dto.AuthElementDto;
import com.founderz.findemy.domain.auth.dto.response.TokenResponse;
import com.founderz.findemy.domain.user.controller.dto.request.AuthRequest;
import com.founderz.findemy.global.exception.domain.student.UserNotFoundException;
import com.founderz.findemy.global.exception.domain.user.PasswordMismatchException;
import com.founderz.findemy.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademyService {

    private final AcademyRepository academyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void registerAcademy(AcademyRequest request) {
        academyRepository.save(
                Academy.builder()
                        .accountId(request.account_id())
                        .password(passwordEncoder.encode(request.password()))
                        .academyName(request.academy_name())
                        .sido(request.sido())
                        .sigungu(request.sigungu())
                        .build());
    }

    @Transactional
    public TokenResponse login(AuthRequest request) {
        Academy academy = academyRepository.findByAccountId(request.account_id())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.password(), academy.getPassword())) throw PasswordMismatchException.EXCEPTION;

        return jwtTokenProvider.receiveToken(request.account_id(), AuthElementDto.UserRole.ACADEMY);
    }

    @Transactional(readOnly = true)
    public List<AcademyResponse> findAllAcademies() {
        return academyRepository.findAll()
                .stream()
                .map(AcademyResponse::of)
                .toList();
    }
}
