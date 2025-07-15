package com.founderz.findemy.domain.academy.controller.dto.response;

import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;

import java.util.List;

public record AcademyResponse(
        Long academy_id,
        String academy_name,
        String academy_img_url,
        String address,
        List<Subject> subjects
) {
    public static AcademyResponse of(Academy academy) {
        return new AcademyResponse(academy.getId(), academy.getAcademyName(), academy.getAcademyImgUrl(), academy.getAddress(), academy.getSubjects());
    }
}
