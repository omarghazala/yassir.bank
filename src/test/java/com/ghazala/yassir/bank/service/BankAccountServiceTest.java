package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.BankAccountBalanceDTO;
import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.CustomerNotFoundException;
import com.ghazala.yassir.bank.mapper.BankAccountMapper;
import com.ghazala.yassir.bank.repository.BankAccountRepository;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    BankAccountServiceImpl bankAccountService;


    @Test
    public void bankAccountService_CreateBankAccount_ReturnOneEntry() throws CustomerNotFoundException {
        Customer customer  = Customer.builder().id(1L).name("Omar Ghazala").build();

        BankAccountDTO bankAccountDTO = BankAccountDTO.builder()
                .initialDeposit(1000.0)
                .customerId(customer.getId()).build();

        BankAccount bankAccount = BankAccountMapper.bankAccountDtoToBankAccount(bankAccountDTO,customer);

        when(customerRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(customer));
        when(bankAccountRepository.save(Mockito.any(BankAccount.class)))
                .thenReturn(bankAccount);

        BankAccountDTO savedBankAccount = bankAccountService.createBankAccount(customer.getId(), bankAccountDTO);

        Assertions.assertNotNull(savedBankAccount);
    }

    @Test
    public void bankAccountService_RetrieveBalance_ReturnOneEntry() throws BankAccountNotFoundException {
        Customer customer  = Customer.builder().id(1L).name("Omar Ghazala").build();

        BankAccount bankAccount = BankAccount.builder().id(1L).balance(1000.0).customer(customer).build();
        when(bankAccountRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(bankAccount));

        BankAccountBalanceDTO bankAccountBalanceDTO = bankAccountService.retrieveBalance(bankAccount.getId());

        Assertions.assertNotNull(bankAccountBalanceDTO);

    }



}


