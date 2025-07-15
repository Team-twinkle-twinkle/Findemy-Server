package com.founderz.findemy.domain.favorite.controller;

import com.founderz.findemy.domain.favorite.controller.dto.FavoriteResponse;
import com.founderz.findemy.domain.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public void addFavorite(@RequestParam Long academyId, @RequestBody List<Long> lessonIds) {
        favoriteService.addFavorite(academyId, lessonIds);
    }

    @GetMapping
    public List<FavoriteResponse> getFavorites() {
        return favoriteService.getFavorites();
    }

    @DeleteMapping
    public void removeFavorite(@RequestParam Long academyId) {
        favoriteService.removeFavorite(academyId);
    }
}
