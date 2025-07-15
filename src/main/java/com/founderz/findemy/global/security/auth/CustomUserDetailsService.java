package com.founderz.findemy.global.security.auth;

import com.founderz.findemy.domain.academy.repository.AcademyRepository;
import com.founderz.findemy.domain.auth.dto.AuthElementDto;
import com.founderz.findemy.domain.user.repository.UserRepository;
import com.founderz.findemy.global.exception.domain.academy.AcademyNotFoundException;
import com.founderz.findemy.global.exception.domain.auth.InvalidTokenException;
import com.founderz.findemy.global.exception.domain.student.UserNotFoundException;
import com.founderz.findemy.global.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AcademyRepository academyRepository;
    private final JwtProperties jwtProperties;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        var parts = username.split(":");

        var userId = parts[0];
        var userSecretId = parts[1];
        String type;

        if (userSecretId.equals(jwtProperties.teacherSecret())) {
            type = handleAcademy(userId);
        } else if (userSecretId.equals(jwtProperties.studentSecret())) {
            type = handleUser(userId);
        } else {
            throw InvalidTokenException.EXCEPTION;
        }

        return new CustomUserDetails(userId, type);
    }

    private String handleAcademy(String academyId) {
        if (!academyRepository.existsByAccountId(academyId)) {
            throw AcademyNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.ACADEMY.name();
    }

    private String handleUser(String userId) {
        if (!userRepository.existsByAccountId(userId)) {
            throw UserNotFoundException.EXCEPTION;
        }

        return AuthElementDto.UserRole.USER.name();
    }

}
