package com.founderz.findemy.domain.academy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tbl_academy")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String academyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sido", nullable = false)
    private SiDo sido;

    @Enumerated(EnumType.STRING)
    @Column(name = "sigungu", nullable = false)
    private SiGunGu sigungu;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject", nullable = false)
    private Subject subject;
}