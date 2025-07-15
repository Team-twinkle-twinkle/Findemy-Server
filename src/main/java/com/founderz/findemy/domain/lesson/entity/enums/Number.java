package com.founderz.findemy.domain.lesson.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Number {
    ONCE("주 1회"),
    TWICE("주 2회"),
    THREE_TIMES("주 3회"),
    FOUR_TIMES("주 4회"),
    FIVE_TIMES("주 5회"),
    SIX_TIMES("주 6회");

    private final String name;
}
