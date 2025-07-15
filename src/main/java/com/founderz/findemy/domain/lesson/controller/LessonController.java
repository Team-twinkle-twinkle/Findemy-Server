package com.founderz.findemy.domain.lesson.controller;

import com.founderz.findemy.domain.lesson.controller.dto.LessonDto;
import com.founderz.findemy.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/register")
    public void registerLesson(@RequestBody List<LessonDto> requests) {
        lessonService.registerLesson(requests);
    }
}
