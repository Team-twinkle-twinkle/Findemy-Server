package com.founderz.findemy.domain.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Subject {
    KOREAN("과목, 국어"),
    MATH("과목, 수학"),
    SOCIAL("과목, 사회"),
    SCIENCE("과목, 과학"),
    ENGLISH("과목, 영어");

    private final String name;
}
