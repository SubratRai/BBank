package com.Subrat.BBank.service;

import com.Subrat.BBank.dto.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
}
