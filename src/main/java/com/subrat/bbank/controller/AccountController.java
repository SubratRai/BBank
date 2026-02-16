package com.subrat.bbank.controller;

import com.subrat.bbank.dto.*;
import com.subrat.bbank.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountCreateRequest request,
                                                         Authentication authentication) {
        return ResponseEntity.ok(accountService.createAccount(request, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getMyAccounts(Authentication authentication) {
        return ResponseEntity.ok(accountService.getMyAccounts(authentication.getName()));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountDetails(@PathVariable String accountNumber,
                                                             Authentication authentication) {
        return ResponseEntity.ok(accountService.getAccountDetails(accountNumber, authentication.getName()));
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountResponse> deposit(@PathVariable String accountNumber,
                                                   @Valid @RequestBody AmountRequest request,
                                                   Authentication authentication) {
        return ResponseEntity.ok(accountService.deposit(accountNumber, request, authentication.getName()));
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable String accountNumber,
                                                    @Valid @RequestBody AmountRequest request,
                                                    Authentication authentication) {
        return ResponseEntity.ok(accountService.withdraw(accountNumber, request, authentication.getName()));
    }

    @PostMapping("/{accountNumber}/transfer")
    public ResponseEntity<ApiResponse> transfer(@PathVariable String accountNumber,
                                                @Valid @RequestBody TransferRequest request,
                                                Authentication authentication) {
        return ResponseEntity.ok(accountService.transfer(accountNumber, request, authentication.getName()));
    }

    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<Page<TransactionResponse>> history(@PathVariable String accountNumber,
                                                             Pageable pageable,
                                                             Authentication authentication) {
        return ResponseEntity.ok(accountService.transactions(accountNumber, authentication.getName(), pageable));
    }
}
