package com.founderz.findemy.domain.academy.controller.dto.response;

import com.founderz.findemy.domain.academy.entity.enums.SiDo;
import com.founderz.findemy.domain.academy.entity.enums.SiGunGu;
import com.founderz.findemy.domain.lesson.controller.dto.LessonDto;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;

import java.util.List;

public record AcademyDetail(
        Long academy_id,
        String academy_name,
        String academy_img_url,
        SiDo si_do,
        SiGunGu si_gun_gu,
        String introduction,
        String address,
        String tel_number,
        List<Subject> subjects,
        List<LessonDto> lessons
) {
}
