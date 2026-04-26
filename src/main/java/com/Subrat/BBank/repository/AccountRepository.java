package com.Subrat.BBank.repository;

import com.Subrat.BBank.entity.Account;
import com.Subrat.BBank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findAllByUser(User user);
}
