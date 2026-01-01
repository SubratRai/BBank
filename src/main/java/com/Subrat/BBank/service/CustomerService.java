package com.Subrat.BBank.service;

import com.Subrat.BBank.entity.Customer;
import com.Subrat.BBank.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer saveCustomer(Customer customer){

        Customer customer1=customerRepo.save(customer);
        return customer1;
    }

    public List<Customer> getCustomer(){
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerByAadhaarNumber(String aadhaarNumber){
        return customerRepo.findByAadhaarNumber(aadhaarNumber);
    }

    public Optional<Customer> getCustomerByPanNumber(String panNumber){
        return customerRepo.findByPanNumber(panNumber);
    }

    public List<Customer> getCustomerByFirstName(String firstName){
        return customerRepo.findByFirstName(firstName);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    public void deleteAll(){
        customerRepo.deleteAll();
    }
}
