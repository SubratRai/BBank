package com.Subrat.BBank.dto;

import com.Subrat.BBank.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateRequest {
    @NotNull
    private AccountType accountType;
}
