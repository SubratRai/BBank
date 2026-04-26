package com.Subrat.BBank.dto;

import com.Subrat.BBank.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private Role role;
}
