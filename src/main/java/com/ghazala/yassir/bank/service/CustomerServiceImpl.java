package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.CustomerDTO;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer createCustomerProfile(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> createMultiplesCustomerProfiles(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

}
