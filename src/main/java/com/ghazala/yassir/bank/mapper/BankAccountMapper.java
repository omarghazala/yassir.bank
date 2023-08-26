package com.ghazala.yassir.bank.mapper;

import com.ghazala.yassir.bank.dto.BankAccountBalanceDTO;
import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.entity.BankAccount;
import com.ghazala.yassir.bank.entity.Customer;

public class BankAccountMapper {

    public static BankAccountDTO bankAccountToBankAccountDto(BankAccount bankAccount){
        BankAccountDTO bankAccountDTO = new BankAccountDTO(bankAccount.getBalance(),bankAccount.getId());
        return bankAccountDTO;
    }

    public static BankAccount bankAccountDtoToBankAccount(BankAccountDTO bankAccountDTO, Customer customer){
        BankAccount bankAccount = new BankAccount(bankAccountDTO.getInitialDeposit(),customer);
        return bankAccount;
    }

    public static BankAccountBalanceDTO bankAccountToBankAccountBalanceDTO(BankAccount bankAccount){
        BankAccountBalanceDTO bankAccountBalanceDTO = new BankAccountBalanceDTO(bankAccount.getBalance());
        return bankAccountBalanceDTO;
    }
}
