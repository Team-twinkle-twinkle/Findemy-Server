package com.founderz.findemy.domain.academy.service;

import com.founderz.findemy.domain.academy.controller.dto.request.AcademyInfo;
import com.founderz.findemy.domain.academy.controller.dto.request.AcademyRequest;
import com.founderz.findemy.domain.academy.controller.dto.response.AcademyDetail;
import com.founderz.findemy.domain.academy.controller.dto.response.AcademyResponse;
import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.academy.repository.AcademyRepository;
import com.founderz.findemy.domain.academy.service.component.AcademyFacade;
import com.founderz.findemy.domain.auth.dto.AuthElementDto;
import com.founderz.findemy.domain.auth.dto.response.TokenResponse;
import com.founderz.findemy.domain.lesson.controller.dto.LessonDto;
import com.founderz.findemy.domain.lesson.repository.LessonRepository;
import com.founderz.findemy.domain.user.controller.dto.request.AuthRequest;
import com.founderz.findemy.global.exception.domain.academy.AcademyNotFoundException;
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
    private final AcademyFacade facade;
    private final LessonRepository lessonRepository;
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
    public void saveInfo(AcademyInfo request) {
        Academy academy = facade.myAcademy(); // 실제 영속 엔티티
        academy.updateInfo(
                request.academy_name(),
                request.address(),
                request.tel_number(),
                request.academy_img_url(),
                request.introduction(),
                request.si_do(),
                request.si_gun_gu()
        );
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
        return academyRepository.findAllWithSubjects()
                .stream()
                .map(AcademyResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public AcademyDetail findAcademyDetail(Long id) {
        Academy academy = academyRepository.findByIdWithSubjects(id)
                .orElseThrow(()-> AcademyNotFoundException.EXCEPTION);

        List<LessonDto> lessonList = lessonRepository.findAllByAcademy(academy)
                .stream()
                .map(LessonDto::of)
                .toList();

        return new AcademyDetail(
                academy.getId(),
                academy.getAcademyName(),
                academy.getAcademyImgUrl(),
                academy.getSido(),
                academy.getSigungu(),
                academy.getIntroduction(),
                academy.getAddress(),
                academy.getTelNumber(),
                academy.getSubjects(),
                lessonList
        );
    }

    @Transactional(readOnly = true)
    public List<AcademyResponse> searchAcademiesByName(String academyName) {
        return academyRepository.findByNameContainingWithSubjects(academyName)
                .stream()
                .map(AcademyResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public AcademyInfo findAcademyInfo() {
        Academy academy = facade.myAcademy();

        return new AcademyInfo(
                academy.getAcademyName(),
                academy.getAddress(),
                academy.getTelNumber(),
                academy.getAcademyImgUrl(),
                academy.getIntroduction(),
                academy.getSido(),
                academy.getSigungu());
    }
}
