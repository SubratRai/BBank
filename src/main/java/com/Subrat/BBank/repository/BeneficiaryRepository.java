package com.Subrat.BBank.repository;

import com.Subrat.BBank.entity.Beneficiary;
import com.Subrat.BBank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findAllByUser(User user);
}
