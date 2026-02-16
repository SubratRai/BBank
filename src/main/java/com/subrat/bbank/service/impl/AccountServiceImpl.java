package com.subrat.bbank.service.impl;

import com.subrat.bbank.dto.*;
import com.subrat.bbank.entity.Account;
import com.subrat.bbank.entity.Transaction;
import com.subrat.bbank.entity.User;
import com.subrat.bbank.enums.TransactionType;
import com.subrat.bbank.exception.BadRequestException;
import com.subrat.bbank.exception.ResourceNotFoundException;
import com.subrat.bbank.repository.AccountRepository;
import com.subrat.bbank.repository.TransactionRepository;
import com.subrat.bbank.repository.UserRepository;
import com.subrat.bbank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final SecureRandom random = new SecureRandom();

    @Override
    @Transactional
    public AccountResponse createAccount(AccountCreateRequest request, String email) {
        User user = findUserByEmail(email);
        Account account = Account.builder()
                .accountNumber(generateUniqueAccountNumber())
                .balance(BigDecimal.ZERO)
                .accountType(request.getAccountType())
                .frozen(false)
                .user(user)
                .build();

        return mapAccount(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> getMyAccounts(String email) {
        User user = findUserByEmail(email);
        return accountRepository.findAllByUser(user).stream().map(this::mapAccount).toList();
    }

    @Override
    public AccountResponse getAccountDetails(String accountNumber, String email) {
        Account account = getOwnedAccount(accountNumber, email);
        return mapAccount(account);
    }

    @Override
    @Transactional
    public AccountResponse deposit(String accountNumber, AmountRequest request, String email) {
        Account account = getOwnedAccount(accountNumber, email);
        assertActive(account);
        account.setBalance(account.getBalance().add(request.getAmount()));
        transactionRepository.save(Transaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(request.getAmount())
                .destinationAccount(account)
                .build());

        return mapAccount(account);
    }

    @Override
    @Transactional
    public AccountResponse withdraw(String accountNumber, AmountRequest request, String email) {
        Account account = getOwnedAccount(accountNumber, email);
        assertActive(account);
        ensureSufficientBalance(account, request.getAmount());

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        transactionRepository.save(Transaction.builder()
                .type(TransactionType.WITHDRAW)
                .amount(request.getAmount())
                .sourceAccount(account)
                .build());

        return mapAccount(account);
    }

    @Override
    @Transactional
    public ApiResponse transfer(String sourceAccountNumber, TransferRequest request, String email) {
        Account sourceAccount = getOwnedAccount(sourceAccountNumber, email);
        Account destinationAccount = accountRepository.findByAccountNumber(request.getDestinationAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found"));

        if (sourceAccount.getAccountNumber().equals(destinationAccount.getAccountNumber())) {
            throw new BadRequestException("Cannot transfer to same account");
        }

        assertActive(sourceAccount);
        assertActive(destinationAccount);
        ensureSufficientBalance(sourceAccount, request.getAmount());

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(request.getAmount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));

        transactionRepository.save(Transaction.builder()
                .type(TransactionType.TRANSFER)
                .amount(request.getAmount())
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .build());

        return new ApiResponse("Transfer successful");
    }

    @Override
    public Page<TransactionResponse> transactions(String accountNumber, String email, Pageable pageable) {
        Account account = getOwnedAccount(accountNumber, email);
        return transactionRepository.findBySourceAccountOrDestinationAccountOrderByTimestampDesc(account, account, pageable)
                .map(this::mapTransaction);
    }

    @Override
    @Transactional
    public AccountResponse freezeAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setFrozen(true);
        return mapAccount(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::mapAccount).toList();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Account getOwnedAccount(String accountNumber, String email) {
        User user = findUserByEmail(email);
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (!account.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You do not own this account");
        }
        return account;
    }

    private void ensureSufficientBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new BadRequestException("Insufficient balance");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Negative balance is not allowed");
        }
    }

    private void assertActive(Account account) {
        if (account.isFrozen()) {
            throw new BadRequestException("Account is frozen");
        }
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf(1000000000L + Math.abs(random.nextLong() % 9000000000L));
        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

    private AccountResponse mapAccount(Account account) {
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .accountType(account.getAccountType())
                .frozen(account.isFrozen())
                .ownerEmail(account.getUser().getEmail())
                .build();
    }

    private TransactionResponse mapTransaction(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .timestamp(transaction.getTimestamp())
                .sourceAccountNumber(transaction.getSourceAccount() == null ? null : transaction.getSourceAccount().getAccountNumber())
                .destinationAccountNumber(transaction.getDestinationAccount() == null ? null : transaction.getDestinationAccount().getAccountNumber())
                .build();
    }
}
