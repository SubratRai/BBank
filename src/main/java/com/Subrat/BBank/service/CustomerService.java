package com.Subrat.BBank.service;

import com.Subrat.BBank.entity.Customer;
import com.Subrat.BBank.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer){

        Customer cs=customerRepo.save(customer);
        return cs;
    }

    public List<Customer> getCustomer(){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerByAadhaar(String AadhaarNumber){
        return customerRepo.findByAadhaarNumber(AadhaarNumber);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    public void deleteAll(){
        customerRepo.deleteAll();
    }
}
