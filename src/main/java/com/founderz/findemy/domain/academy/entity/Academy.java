package com.founderz.findemy.domain.academy.entity;

import com.founderz.findemy.domain.academy.entity.enums.SiDo;
import com.founderz.findemy.domain.academy.entity.enums.SiGunGu;
import com.founderz.findemy.domain.lesson.entity.enums.Grade;
import com.founderz.findemy.domain.lesson.entity.enums.Number;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column
    private String address;

    @Column
    private String telNumber;

    @Column
    private String academyImgUrl;

    @Column
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(name = "si_do", nullable = false)
    private SiDo sido;

    @Enumerated(EnumType.STRING)
    @Column(name = "si_gun_gu", nullable = false)
    private SiGunGu sigungu;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "academy_subjects",
            joinColumns = @JoinColumn(name = "academy_id")
    )
    @Column(name = "subject")
    private List<Subject> subjects;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "academy_grades",
            joinColumns = @JoinColumn(name = "academy_id")
    )
    @Column(name = "grade")
    private List<Grade> grades;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "academy_numbers",
            joinColumns = @JoinColumn(name = "academy_id")
    )
    @Column(name = "number")
    private List<Number> numbers;
}