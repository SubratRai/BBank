package com.Subrat.BBank.repository;

import com.Subrat.BBank.entity.Customer;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findByAadhaarNumber(String AadhaarNumber);
}
