package com.Subrat.BBank.repository;

import com.Subrat.BBank.entity.Account;
import com.Subrat.BBank.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findBySourceAccountOrDestinationAccountOrderByTimestampDesc(Account source, Account destination, Pageable pageable);
}
