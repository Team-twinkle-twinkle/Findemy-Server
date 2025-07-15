package com.founderz.findemy.domain.academy.controller;

import com.founderz.findemy.domain.academy.controller.dto.request.AcademyInfo;
import com.founderz.findemy.domain.academy.controller.dto.request.AcademyRequest;
import com.founderz.findemy.domain.academy.controller.dto.response.AcademyDetail;
import com.founderz.findemy.domain.academy.controller.dto.response.AcademyResponse;
import com.founderz.findemy.domain.academy.service.AcademyService;
import com.founderz.findemy.domain.auth.dto.response.TokenResponse;
import com.founderz.findemy.domain.user.controller.dto.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academy")
@RequiredArgsConstructor
public class AcademyController {

    private final AcademyService academyService;

    @PostMapping("/register")
    public void registerAcademy(@RequestBody AcademyRequest request) {
        academyService.registerAcademy(request);
    }

    @PostMapping("/save-info")
    public void saveInfo(@RequestBody AcademyInfo request) {
        academyService.saveInfo(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthRequest request) {
        return academyService.login(request);
    }

    @GetMapping("/all")
    public List<AcademyResponse> findAllAcademies() {
        return academyService.findAllAcademies();
    }

    @GetMapping("/{id}")
    public AcademyDetail findAcademyDetail(@PathVariable Long id) {
        return academyService.findAcademyDetail(id);
    }

    @GetMapping("/search")
    public List<AcademyResponse> searchAcademiesByName(@RequestParam String academyName) {
        return academyService.searchAcademiesByName(academyName);
    }
}
