package com.subrat.bbank.dto;

import com.subrat.bbank.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private Role role;
}
