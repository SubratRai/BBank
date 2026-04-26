package com.Subrat.BBank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeneficiaryResponse {
    private Long id;
    private String name;
    private String accountNumber;
    private String bankName;
}
