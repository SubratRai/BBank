package com.subrat.bbank.service;

import com.subrat.bbank.dto.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
}
