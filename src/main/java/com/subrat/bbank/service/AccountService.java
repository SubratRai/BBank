package com.subrat.bbank.service;

import com.subrat.bbank.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountCreateRequest request, String email);
    List<AccountResponse> getMyAccounts(String email);
    AccountResponse getAccountDetails(String accountNumber, String email);
    AccountResponse deposit(String accountNumber, AmountRequest request, String email);
    AccountResponse withdraw(String accountNumber, AmountRequest request, String email);
    ApiResponse transfer(String sourceAccountNumber, TransferRequest request, String email);
    Page<TransactionResponse> transactions(String accountNumber, String email, Pageable pageable);
    AccountResponse freezeAccount(String accountNumber);
    List<AccountResponse> getAllAccounts();
}
