package com.subrat.bbank.controller;

import com.subrat.bbank.dto.AccountResponse;
import com.subrat.bbank.dto.UserResponse;
import com.subrat.bbank.service.AccountService;
import com.subrat.bbank.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final AccountService accountService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> users() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> accounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PatchMapping("/accounts/{accountNumber}/freeze")
    public ResponseEntity<AccountResponse> freeze(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.freezeAccount(accountNumber));
    }
}
