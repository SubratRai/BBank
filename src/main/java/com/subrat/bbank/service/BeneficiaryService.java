package com.subrat.bbank.service;

import com.subrat.bbank.dto.BeneficiaryRequest;
import com.subrat.bbank.dto.BeneficiaryResponse;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryResponse addBeneficiary(BeneficiaryRequest request, String email);
    List<BeneficiaryResponse> getBeneficiaries(String email);
}
