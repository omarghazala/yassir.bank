package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.entity.Transaction;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.repository.BankAccountRepository;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import com.ghazala.yassir.bank.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;


    @Test
    public void transactionService_geTransactionHistory_ReturnList() throws BankAccountNotFoundException {
        Customer customer  = Customer.builder().id(1L).name("Omar Ghazala").build();

        BankAccount bankAccount = BankAccount.builder().id(1L).balance(1000.0).customer(customer).build();
        BankAccount bankAccount2 = BankAccount.builder().id(3L).balance(1000.0).customer(customer).build();

        List<Transaction> transactionList = new ArrayList<>();

        Transaction transaction1 = Transaction.builder()
                .id(101L).amount(100.0)
                .sourceBankAccount(bankAccount)
                .targetBankAccount(bankAccount2)
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(101L).amount(300.0)
                .sourceBankAccount(bankAccount)
                .targetBankAccount(bankAccount2)
                .build();

        transactionList.add(transaction1);
        transactionList.add(transaction2);

        when(bankAccountRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.of(bankAccount));

        when(transactionRepository.findAllBySourceBankAccountIdOrTargetBankAccountId(Mockito.any(Long.class),Mockito.any(Long.class)))
                .thenReturn(transactionList);

        List<TransactionDTO> transactionDTOList = transactionService.getTransactionHistory(bankAccount.getId());

        Assertions.assertNotNull(transactionDTOList);
        Assertions.assertTrue(transactionDTOList.size()>0);

    }
}
