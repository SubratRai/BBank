package com.Subrat.BBank.controller;

import com.Subrat.BBank.dto.AccountResponse;
import com.Subrat.BBank.dto.UserResponse;
import com.Subrat.BBank.service.AccountService;
import com.Subrat.BBank.service.AdminService;
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
