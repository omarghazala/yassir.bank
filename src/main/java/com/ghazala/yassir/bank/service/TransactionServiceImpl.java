package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Transaction;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.InsufficientFundsException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import com.ghazala.yassir.bank.mapper.TransactionMapper;
import com.ghazala.yassir.bank.repository.BankAccountRepository;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import com.ghazala.yassir.bank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public TransactionDTO transferAmount(TransactionDTO transactionDTO) throws BankAccountNotFoundException, InsufficientFundsException, NegativeFundsException {

        BankAccount sourceBankAccount = bankAccountRepository.findById(transactionDTO.getSourceBankAccountId()).orElseThrow(
                ()-> new BankAccountNotFoundException("Customer not found with ID : "+transactionDTO.getSourceBankAccountId())
        );

        BankAccount targetBankAccount = bankAccountRepository.findById(transactionDTO.getTargetBankAccountId()).orElseThrow(
                ()-> new BankAccountNotFoundException("Customer not found with ID : "+transactionDTO.getTargetBankAccountId())
        );

        if(transactionDTO.getAmount()<=0){
            throw new NegativeFundsException("Only Positive Funds Allowed");
        }

        if(sourceBankAccount.getBalance().compareTo(transactionDTO.getAmount())<0){
            throw new InsufficientFundsException("Insufficient funds in the source account : " +transactionDTO.getSourceBankAccountId());
        }

        sourceBankAccount.setBalance(sourceBankAccount.getBalance() - transactionDTO.getAmount());
        targetBankAccount.setBalance(targetBankAccount.getBalance() + transactionDTO.getAmount());
        bankAccountRepository.save(sourceBankAccount);
        bankAccountRepository.save(targetBankAccount);

        Transaction transaction = Transaction.builder()
                .amount(transactionDTO.getAmount())
                .sourceBankAccount(sourceBankAccount)
                .targetBankAccount(targetBankAccount).build();

        transactionRepository.save(transaction);


        return transactionDTO;
    }

    public List<TransactionDTO> getTransactionHistory(Long bankAccountId) throws BankAccountNotFoundException{
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(
                ()-> new BankAccountNotFoundException("Bank Account not found with ID : "+bankAccountId)
        );

        List<Transaction> transaction = transactionRepository.findAllBySourceBankAccountIdOrTargetBankAccountId(bankAccountId,bankAccountId);
        List<TransactionDTO> transactionDTOList = TransactionMapper.transactionListToTransactionDTOList(transaction);

        return transactionDTOList;
    }
}
