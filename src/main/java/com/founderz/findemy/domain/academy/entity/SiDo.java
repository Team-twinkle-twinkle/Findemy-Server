package com.founderz.findemy.domain.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SiDo {
    SEOUL("서울시"),
    BUSAN("부산"),
    DAEGU("대구"),
    INCHEON("인천"),
    GWANGJU("광주"),
    DAEJEON("대전"),
    ULSAN("울산"),
    SEJONG("세종"),
    GYEONGGI_DO("경기도"),
    CHUNGBUK("충청북도"),
    CHUNGNAM("충청남도"),
    JEONNAM("전라남도"),
    JEONBUK("전라북도"),
    GYEONGNAM("경상남도"),
    GYEONGBUK("경상북도"),
    GANGWON_DO("강원도"),
    JEGU("제주도");

    private final String name;
}
