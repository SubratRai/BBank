package com.Subrat.BBank.controller;

import com.Subrat.BBank.entity.Customer;
import com.Subrat.BBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer/")
public class CustomerController {

    //this print method is just for test on webpage
    @GetMapping("y/")
    public String print(){
        return "printing";
    }

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping()
    public List<Customer> getALL(){
        return customerService.getCustomer();
    }

    @GetMapping("aadhaarNumber/{aadhaarNumber}")
    public Optional<Customer> getByAadhaarNumber(@PathVariable String aadhaarNumber){
        return customerService.getCustomerByAadhaarNumber(aadhaarNumber);
    }

    @GetMapping("panNumber/{panNumber}")
    public Optional<Customer> getByPanNumber(@PathVariable String panNumber){
        return customerService.getCustomerByPanNumber(panNumber);
    }

    @GetMapping("first_name/{firstName}")
    public List<Customer> getByFirstName(@PathVariable String firstName){
        return customerService.getCustomerByFirstName(firstName);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomer(@PathVariable Long id){
                customerService.deleteCustomer(id);
    }

}
