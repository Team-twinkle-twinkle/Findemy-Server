package com.founderz.findemy.domain.lesson.repository;

import com.founderz.findemy.domain.lesson.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
