package com.founderz.findemy.domain.favorite.controller.dto;

import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.lesson.controller.dto.LessonDto;
import com.founderz.findemy.domain.lesson.entity.Lesson;

import java.util.List;

public record FavoriteResponse(
        Long academy_id,
        String academy_name,
        String academy_img_url,
        String address,
        List<LessonDto> selectedLessons,
        Long totalPrice
) {
    public FavoriteResponse(Academy academy, List<Lesson> lessons, Long totalPrice) {
        this(
                academy.getId(),
                academy.getAcademyName(),
                academy.getAcademyImgUrl(),
                academy.getAddress(),
                lessons.stream().map(LessonDto::of).toList(),
                totalPrice
        );
    }
}