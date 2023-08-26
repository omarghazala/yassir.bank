package com.ghazala.yassir.bank.repository;

import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.repository.BankAccountRepository;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BankAccountRepositoryTest {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void bankAccountRepository_TransferAmount_ReturnOneEntry(){

        Customer customer  = Customer.builder().id(100L).name("Omar Ghazala").build();

        Customer savedCustomer = customerRepository.save(customer);

        BankAccount bankAccount = BankAccount.builder().balance(20000.0).customer(savedCustomer).build();

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        Assertions.assertNotNull(savedBankAccount);
        Assertions.assertTrue(savedBankAccount.getId()>0);
    }

    @Test
    public void bankAccountRepository_RetrieveBankAccountBalance_ReturnOneEntry() throws BankAccountNotFoundException {

        Customer customer  = Customer.builder().id(100L).name("Omar Ghazala").build();

        Customer savedCustomer = customerRepository.save(customer);

        BankAccount bankAccount = BankAccount.builder().balance(20000.0).customer(savedCustomer).build();

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccount retirvedBankAccount =  bankAccountRepository.findById(savedBankAccount.getId()).orElseThrow(
                ()-> new BankAccountNotFoundException("Bank Account not found with ID : "+savedBankAccount.getId())
        );;

        Assertions.assertNotNull(retirvedBankAccount);
        Assertions.assertTrue(retirvedBankAccount.getBalance()>=0);
    }
}
