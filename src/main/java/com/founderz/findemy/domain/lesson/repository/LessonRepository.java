package com.founderz.findemy.domain.lesson.repository;

import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.lesson.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByAcademy(Academy academy);
}
