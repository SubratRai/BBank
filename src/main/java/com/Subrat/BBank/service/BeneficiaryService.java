package com.Subrat.BBank.service;

import com.Subrat.BBank.dto.BeneficiaryRequest;
import com.Subrat.BBank.dto.BeneficiaryResponse;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryResponse addBeneficiary(BeneficiaryRequest request, String email);
    List<BeneficiaryResponse> getBeneficiaries(String email);
}
