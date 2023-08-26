package com.ghazala.yassir.bank.repository;

import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.entity.Transaction;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionRepositoryTest {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Test
    public void TransactionRepository_TransferAmount_ReturnOneEntry(){

        Customer sourceCustomer  = Customer.builder().id(100L).name("Omar Ghazala").build();
        Customer savedSourceCustomer = customerRepository.save(sourceCustomer);
        BankAccount sourceBankAccount = BankAccount.builder().balance(20000.0).customer(savedSourceCustomer).build();
        BankAccount savedSourceBankAccount = bankAccountRepository.save(sourceBankAccount);

        Customer targetCustomer  = Customer.builder().id(101L).name("Omar Hazem").build();
        Customer savedTargetCustomer = customerRepository.save(targetCustomer);
        BankAccount targetBankAccount = BankAccount.builder().balance(20000.0).customer(savedTargetCustomer).build();
        BankAccount savedTargetBankAccount = bankAccountRepository.save(targetBankAccount);

        Transaction transaction = Transaction.builder()
                .amount(500.0)
                .sourceBankAccount(savedSourceBankAccount)
                .targetBankAccount(savedTargetBankAccount).build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        Assertions.assertNotNull(savedTransaction);
    }

    @Test
    public void TransactionRepository_GetHistory_ReturnList(){

        Customer sourceCustomer  = Customer.builder().id(100L).name("Omar Ghazala").build();
        Customer savedSourceCustomer = customerRepository.save(sourceCustomer);
        BankAccount sourceBankAccount = BankAccount.builder().balance(20000.0).customer(savedSourceCustomer).build();
        BankAccount savedSourceBankAccount = bankAccountRepository.save(sourceBankAccount);

        Customer targetCustomer  = Customer.builder().id(101L).name("Omar Hazem").build();
        Customer savedTargetCustomer = customerRepository.save(targetCustomer);
        BankAccount targetBankAccount = BankAccount.builder().balance(20000.0).customer(savedTargetCustomer).build();
        BankAccount savedTargetBankAccount = bankAccountRepository.save(targetBankAccount);

        Transaction transaction = Transaction.builder()
                .amount(500.0)
                .sourceBankAccount(savedSourceBankAccount)
                .targetBankAccount(savedTargetBankAccount).build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        Customer targetCustomer2  = Customer.builder().id(102L).name("Diaa Alaa").build();
        Customer savedTargetCustomer2 = customerRepository.save(targetCustomer2);
        BankAccount targetBankAccount2 = BankAccount.builder().balance(20000.0).customer(savedTargetCustomer2).build();
        BankAccount savedTargetBankAccount2 = bankAccountRepository.save(targetBankAccount2);

        Transaction transaction2 = Transaction.builder()
                .amount(500.0)
                .sourceBankAccount(savedSourceBankAccount)
                .targetBankAccount(savedTargetBankAccount2).build();

        Transaction savedTransaction2 = transactionRepository.save(transaction2);

        List<Transaction> transactionList = transactionRepository.findAllBySourceBankAccountIdOrTargetBankAccountId(
                savedSourceBankAccount.getId(),savedSourceBankAccount.getId()
        );

        Assertions.assertNotNull(transactionList);
        Assertions.assertTrue(transactionList.size()>1);
    }
}
