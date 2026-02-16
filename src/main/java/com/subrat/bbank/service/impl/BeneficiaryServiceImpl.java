package com.subrat.bbank.service.impl;

import com.subrat.bbank.dto.BeneficiaryRequest;
import com.subrat.bbank.dto.BeneficiaryResponse;
import com.subrat.bbank.entity.Beneficiary;
import com.subrat.bbank.entity.User;
import com.subrat.bbank.exception.ResourceNotFoundException;
import com.subrat.bbank.repository.BeneficiaryRepository;
import com.subrat.bbank.repository.UserRepository;
import com.subrat.bbank.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    @Override
    public BeneficiaryResponse addBeneficiary(BeneficiaryRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Beneficiary beneficiary = Beneficiary.builder()
                .name(request.getName())
                .accountNumber(request.getAccountNumber())
                .bankName(request.getBankName())
                .user(user)
                .build();

        return map(beneficiaryRepository.save(beneficiary));
    }

    @Override
    public List<BeneficiaryResponse> getBeneficiaries(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return beneficiaryRepository.findAllByUser(user).stream().map(this::map).toList();
    }

    private BeneficiaryResponse map(Beneficiary b) {
        return BeneficiaryResponse.builder()
                .id(b.getId())
                .name(b.getName())
                .accountNumber(b.getAccountNumber())
                .bankName(b.getBankName())
                .build();
    }
}
