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

    @GetMapping("y/")
    public String print(){
        return "printing";
    }

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
//       return "customer created successfully!";
    }

    @GetMapping()
    public List<Customer> getALL(){
        return customerService.getCustomer();
    }

    @GetMapping("getByAadhaar/")
    public Optional<Customer> getByAadhaar(String Aadhaar){
        return customerService.getCustomerByAadhaar(Aadhaar);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
