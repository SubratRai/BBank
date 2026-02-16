package com.subrat.bbank.repository;

import com.subrat.bbank.entity.Account;
import com.subrat.bbank.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findBySourceAccountOrDestinationAccountOrderByTimestampDesc(Account source, Account destination, Pageable pageable);
}
