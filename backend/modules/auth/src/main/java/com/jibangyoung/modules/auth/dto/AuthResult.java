package com.jibangyoung.modules.auth.dto;

public class AuthResult {
    private final String accessToken;
    private final UserResponse user;

    public AuthResult(String accessToken, UserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() { return accessToken; }
    public UserResponse getUser() { return user; }
}
