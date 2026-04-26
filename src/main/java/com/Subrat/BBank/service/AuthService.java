package com.Subrat.BBank.service;

import com.Subrat.BBank.dto.AuthRequest;
import com.Subrat.BBank.dto.AuthResponse;
import com.Subrat.BBank.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
