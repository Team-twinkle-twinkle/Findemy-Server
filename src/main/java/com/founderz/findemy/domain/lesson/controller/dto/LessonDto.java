package com.founderz.findemy.domain.lesson.controller.dto;

import com.founderz.findemy.domain.lesson.entity.Lesson;
import com.founderz.findemy.domain.lesson.entity.enums.Grade;
import com.founderz.findemy.domain.lesson.entity.enums.Number;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;

public record LessonDto(
        Subject subject,
        Grade grade,
        Number number,
        Long amount
) {
    public static LessonDto of(Lesson lesson) {
        return new LessonDto(lesson.getSubject(), lesson.getGrade(), lesson.getNumber(), lesson.getAmount());
    }
}
