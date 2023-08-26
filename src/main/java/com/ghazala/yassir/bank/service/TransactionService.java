package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.InsufficientFundsException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {
    public TransactionDTO transferAmount(TransactionDTO transactionDTO) throws BankAccountNotFoundException, InsufficientFundsException, NegativeFundsException;
    public List<TransactionDTO> getTransactionHistory(Long bankAccountId) throws BankAccountNotFoundException;

}
