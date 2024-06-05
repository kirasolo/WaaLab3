package com.example.WaaLab3.security.services;

import com.example.WaaLab3.models.dto.auth.LoginRequest;
import com.example.WaaLab3.models.dto.auth.LoginResponse;
import com.example.WaaLab3.models.dto.auth.RefreshTokenRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
