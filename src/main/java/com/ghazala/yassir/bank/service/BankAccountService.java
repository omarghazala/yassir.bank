package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.BankAccountBalanceDTO;
import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.CustomerNotFoundException;
import com.ghazala.yassir.bank.exceptions.InsufficientFundsException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import org.springframework.stereotype.Service;

public interface BankAccountService {
    public BankAccountDTO createBankAccount(Long customerId,BankAccountDTO bankAccountDTO) throws CustomerNotFoundException, NegativeFundsException;
    public BankAccountBalanceDTO retrieveBalance(Long bankAccountId) throws BankAccountNotFoundException;
}
