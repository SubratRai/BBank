package com.subrat.bbank.dto;

import com.subrat.bbank.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private boolean frozen;
    private String ownerEmail;
}
