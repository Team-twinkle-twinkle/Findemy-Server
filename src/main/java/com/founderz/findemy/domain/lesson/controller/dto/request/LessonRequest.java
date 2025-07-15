package com.founderz.findemy.domain.lesson.controller.dto.request;

import com.founderz.findemy.domain.lesson.entity.enums.Grade;
import com.founderz.findemy.domain.lesson.entity.enums.Number;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;

public record LessonRequest(
        Subject subject,
        Grade grade,
        Number number,
        Long amount
) {
}
