package com.ghazala.yassir.bank.service;

import com.ghazala.yassir.bank.dto.BankAccountBalanceDTO;
import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.CustomerNotFoundException;
import com.ghazala.yassir.bank.exceptions.InsufficientFundsException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import com.ghazala.yassir.bank.mapper.BankAccountMapper;
import com.ghazala.yassir.bank.repository.BankAccountRepository;
import com.ghazala.yassir.bank.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BankAccountDTO createBankAccount(Long customerId, BankAccountDTO bankAccountDTO) throws CustomerNotFoundException, NegativeFundsException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Customer not found with ID : "+customerId)
        );

        if(bankAccountDTO.getInitialDeposit()<=0){
            throw new NegativeFundsException("Only Positive Funds Allowed");
        }

        BankAccount createdBankAccount = BankAccountMapper.bankAccountDtoToBankAccount(bankAccountDTO,customer);
        BankAccount bankAccountCreated = bankAccountRepository.save(createdBankAccount);
        bankAccountDTO.setCustomerId(customerId);
        return bankAccountDTO;
    }

    @Override
    public BankAccountBalanceDTO retrieveBalance(Long bankAccountId) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(
                ()-> new BankAccountNotFoundException("Bank Account not found with ID : "+bankAccountId)
        );

        BankAccountBalanceDTO  bankAccountBalanceDTO = BankAccountMapper.bankAccountToBankAccountBalanceDTO(bankAccount);
        return bankAccountBalanceDTO;
    }


}
