package com.subrat.bbank.controller;

import com.subrat.bbank.dto.BeneficiaryRequest;
import com.subrat.bbank.dto.BeneficiaryResponse;
import com.subrat.bbank.service.BeneficiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping
    public ResponseEntity<BeneficiaryResponse> addBeneficiary(@Valid @RequestBody BeneficiaryRequest request,
                                                              Authentication authentication) {
        return ResponseEntity.ok(beneficiaryService.addBeneficiary(request, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<BeneficiaryResponse>> list(Authentication authentication) {
        return ResponseEntity.ok(beneficiaryService.getBeneficiaries(authentication.getName()));
    }
}
