package com.jibangyoung.modules.auth.service;

import org.springframework.stereotype.Service;

import com.jibangyoung.modules.auth.dto.AuthResult;
import com.jibangyoung.modules.auth.dto.LoginRequest;

@Service
public class AuthService {
    public AuthResult login(LoginRequest loginDto) {
        // 1) 사용자 검증
        // 2) 토큰 생성 (JWT)
        // 3) User 정보 반환

        // 예시 (실제 구현 필요)
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI...";
        return new AuthResult(fakeToken, /* UserResponse */ null);
    }
}
