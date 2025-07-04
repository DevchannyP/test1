package com.jibangyoung.modules.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jibangyoung.modules.auth.dto.FindIdRequest;
import com.jibangyoung.modules.auth.dto.FindPasswordRequest;
import com.jibangyoung.modules.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "인증/로그인/소셜 OAuth2 API")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    // 이메일 로그인
    @Operation(summary = "이메일 로그인", description = "이메일과 비밀번호로 로그인하여 JWT를 발급합니다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "JWT 토큰 발급 성공", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "입력값 오류/유저 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping("/email-login")
    @Gzip // (커스텀 Gzip 압축 AOP, 실무 적용)
    @RateLimiter(limit = 10, period = 60) // 1분 10회 제한 예시
    public ResponseEntity<?> loginWithEmail(@Valid @RequestBody AuthRequest.EmailLogin req) {
        try {
            TokenResponse response = authService.loginWithEmail(req);
            // [실무] Set-Cookie: HttpOnly로 Access/Refresh 토큰 전달(필요시)
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e));
        }
    }

    // 소셜 로그인 (카카오/구글/네이버)
    @Operation(summary = "소셜 로그인", description = "OAuth2 (카카오/구글/네이버) 로그인",
        responses = {
            @ApiResponse(responseCode = "200", description = "소셜 로그인 성공", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "소셜 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping("/{provider}-login")
    @RateLimiter(limit = 15, period = 60)
    public ResponseEntity<?> socialLogin(
            @Parameter(description = "provider (kakao/google/naver)", example = "kakao") @PathVariable String provider,
            @Valid @RequestBody AuthRequest.SocialLogin req) {
        try {
            TokenResponse response = authService.loginWithOAuth2(provider, req);
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e));
        }
    }

    // 토큰 갱신 (Refresh Token)
    @Operation(summary = "토큰 재발급", description = "Refresh Token으로 Access Token 재발급",
        responses = {
            @ApiResponse(responseCode = "200", description = "토큰 재발급 성공", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "토큰 만료/유효하지 않음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        try {
            TokenResponse response = tokenService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            return ResponseEntity.status(401).body(ErrorResponse.of(e));
        }
    }

    // 아이디 찾기
    @Operation(summary = "아이디 찾기", description = "가입된 이메일로 사용자 아이디 찾기")
    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@Valid @RequestBody FindIdRequest req) {
        try {
            String userId = authService.findIdByEmail(req);
            return ResponseEntity.ok(userId);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e));
        }
    }

    // 비밀번호 찾기(임시 비밀번호 발급)
    @Operation(summary = "비밀번호 찾기", description = "이메일로 임시 비밀번호/재설정 링크 발송")
    @PostMapping("/find-password")
    public ResponseEntity<?> findPassword(@Valid @RequestBody FindPasswordRequest req) {
        try {
            authService.sendPasswordReset(req);
            return ResponseEntity.ok().build();
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(e));
        }
    }
}
