package com.founderz.findemy.domain.academy.service.component;

import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.academy.repository.AcademyRepository;
import com.founderz.findemy.global.exception.domain.academy.AcademyNotFoundException;
import com.founderz.findemy.global.exception.domain.auth.NotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AcademyFacade {

    private final AcademyRepository academyRepository;

    @Transactional(readOnly = true)
    public Academy myAcademy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증되지 않는 유저입니다.");
        }

        String accountId = authentication.getName();

        return academyRepository.findByAccountId(accountId)
                .orElseThrow(() -> AcademyNotFoundException.EXCEPTION);
    }
}
