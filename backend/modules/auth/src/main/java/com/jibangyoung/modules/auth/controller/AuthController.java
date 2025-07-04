package com.jibangyoung.modules.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jibangyoung.modules.auth.dto.LoginRequest;
import com.jibangyoung.modules.auth.dto.UserResponse;
import com.jibangyoung.modules.auth.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody LoginRequest loginDto,
            HttpServletResponse response
    ) {
        var authResult = authService.login(loginDto);

        Cookie accessTokenCookie = new Cookie("accessToken", authResult.getAccessToken());
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setMaxAge(60 * 60); // 1시간
        accessTokenCookie.setAttribute("SameSite", "Strict"); // Java 11 이상

        response.addCookie(accessTokenCookie);

        // RefreshToken도 필요 시 별도 쿠키로 추가 가능

        return ResponseEntity.ok(authResult.getUser());
    }
}
