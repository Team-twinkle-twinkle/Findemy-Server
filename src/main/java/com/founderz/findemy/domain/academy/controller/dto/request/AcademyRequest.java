package com.founderz.findemy.domain.academy.controller.dto.request;

import com.founderz.findemy.domain.academy.entity.enums.SiDo;
import com.founderz.findemy.domain.academy.entity.enums.SiGunGu;

public record AcademyRequest(
        String account_id,
        String password,
        String academy_name,
        SiDo sido,
        SiGunGu sigungu
) {
}
