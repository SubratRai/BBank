package com.subrat.bbank.service;

import com.subrat.bbank.dto.AuthRequest;
import com.subrat.bbank.dto.AuthResponse;
import com.subrat.bbank.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
