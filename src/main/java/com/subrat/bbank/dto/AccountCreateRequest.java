package com.subrat.bbank.dto;

import com.subrat.bbank.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateRequest {
    @NotNull
    private AccountType accountType;
}
