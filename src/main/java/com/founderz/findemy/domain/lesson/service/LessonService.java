package com.founderz.findemy.domain.lesson.service;

import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.academy.service.component.AcademyFacade;
import com.founderz.findemy.domain.lesson.controller.dto.LessonDto;
import com.founderz.findemy.domain.lesson.entity.Lesson;
import com.founderz.findemy.domain.lesson.entity.enums.Grade;
import com.founderz.findemy.domain.lesson.entity.enums.Number;
import com.founderz.findemy.domain.lesson.entity.enums.Subject;
import com.founderz.findemy.domain.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final AcademyFacade facade;

    @Transactional
    public void registerLesson(List<LessonDto> requests) {
        Academy academy = facade.myAcademy();

        List<Lesson> lessons = requests.stream()
                .map(request -> Lesson.builder()
                        .academy(academy)
                        .subject(request.subject())
                        .grade(request.grade())
                        .number(request.number())
                        .amount(request.amount())
                        .build())
                .toList();

        lessonRepository.saveAll(lessons);

        // 기존 값 불러오기
        Set<Subject> subjectSet = new HashSet<>(academy.getSubjects());
        Set<Grade> gradeSet = new HashSet<>(academy.getGrades());
        Set<Number> numberSet = new HashSet<>(academy.getNumbers());

        // 새로 추가된 값 반영
        for (LessonDto request : requests) {
            subjectSet.add(request.subject());
            gradeSet.add(request.grade());
            numberSet.add(request.number());
        }

        // JPA가 변경 감지하도록 내부 컬렉션을 clear 후 addAll
        academy.getSubjects().clear();
        academy.getSubjects().addAll(subjectSet);

        academy.getGrades().clear();
        academy.getGrades().addAll(gradeSet);

        academy.getNumbers().clear();
        academy.getNumbers().addAll(numberSet);
    }
}

