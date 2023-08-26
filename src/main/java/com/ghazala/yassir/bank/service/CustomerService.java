package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.CustomerDTO;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    public Customer createCustomerProfile(Customer customer);
    public List<Customer> createMultiplesCustomerProfiles(List<Customer> customers);
}
