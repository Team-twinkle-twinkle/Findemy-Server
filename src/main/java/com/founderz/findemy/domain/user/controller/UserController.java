package com.founderz.findemy.domain.user.controller;

import com.founderz.findemy.domain.auth.dto.response.TokenResponse;
import com.founderz.findemy.domain.user.controller.dto.request.AuthRequest;
import com.founderz.findemy.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthRequest request) {
        userService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }
}
