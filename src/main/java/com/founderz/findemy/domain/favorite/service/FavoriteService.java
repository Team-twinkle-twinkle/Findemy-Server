package com.founderz.findemy.domain.favorite.service;

import com.founderz.findemy.domain.academy.controller.dto.response.AcademyResponse;
import com.founderz.findemy.domain.academy.entity.Academy;
import com.founderz.findemy.domain.academy.repository.AcademyRepository;
import com.founderz.findemy.domain.favorite.controller.dto.FavoriteResponse;
import com.founderz.findemy.domain.favorite.entity.Favorite;
import com.founderz.findemy.domain.favorite.repository.FavoriteRepository;
import com.founderz.findemy.domain.lesson.entity.Lesson;
import com.founderz.findemy.domain.lesson.repository.LessonRepository;
import com.founderz.findemy.domain.user.entity.User;
import com.founderz.findemy.domain.user.repository.UserRepository;
import com.founderz.findemy.domain.user.service.component.UserFacade;
import com.founderz.findemy.global.exception.domain.academy.AcademyNotFoundException;
import com.founderz.findemy.global.exception.domain.favorite.FavoriteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final AcademyRepository academyRepository;
    private final LessonRepository lessonRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserFacade facade;

    @Transactional
    public void addFavorite(Long academyId, List<Long> lessonIds) {
        User user = facade.myUser();
        Academy academy = academyRepository.findById(academyId)
                .orElseThrow(()-> AcademyNotFoundException.EXCEPTION);
        List<Lesson> lessons = lessonRepository.findAllById(lessonIds);

        Favorite favorite = Favorite.builder()
                .user(user)
                .academy(academy)
                .selectedLessons(lessons)
                .build();

        favoriteRepository.save(favorite);
    }

    @Transactional(readOnly = true)
    public List<FavoriteResponse> getFavorites() {
        return favoriteRepository.findByUserId(facade.myUser().getId()).stream()
                .map(fav -> {
                    List<Lesson> lessons = fav.getSelectedLessons();
                    long totalPrice = lessons.stream()
                            .mapToLong(Lesson::getAmount)
                            .sum();
                    return new FavoriteResponse(fav.getAcademy(), lessons, totalPrice);
                })
                .toList();
    }

    @Transactional
    public void removeFavorite(Long academyId) {
        Favorite favorite = favoriteRepository.findByUserIdAndAcademyId(facade.myUser().getId(), academyId)
                        .orElseThrow(()-> FavoriteNotFoundException.EXCEPTION);

        favoriteRepository.delete(favorite);
    }
}
