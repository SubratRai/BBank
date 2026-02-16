package com.subrat.bbank.repository;

import com.subrat.bbank.entity.Beneficiary;
import com.subrat.bbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findAllByUser(User user);
}
