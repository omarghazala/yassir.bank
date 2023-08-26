package com.ghazala.yassir.bank.controller;

import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomerProfile(@RequestBody Customer customer){
        Customer createdCustomer = customerService.createCustomerProfile(customer);
        return new ResponseEntity<>(createdCustomer,HttpStatus.CREATED);
    }

    @PostMapping("/createAll")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Customer>> createMultipleCustomersProfile(@RequestBody List<Customer> customers){
        List<Customer> createdCustomers = customerService.createMultiplesCustomerProfiles(customers);
        return new ResponseEntity<>(createdCustomers,HttpStatus.CREATED);
    }

}
