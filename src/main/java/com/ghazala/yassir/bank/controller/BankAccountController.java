package com.ghazala.yassir.bank.controller;

import com.ghazala.yassir.bank.dto.BankAccountBalanceDTO;
import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.CustomerNotFoundException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import com.ghazala.yassir.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankAccountDTO> createBankAccount(@PathVariable long customerId, @RequestBody BankAccountDTO bankAccountDTO) throws CustomerNotFoundException, NegativeFundsException {
        BankAccountDTO createdAccount = bankAccountService.createBankAccount(customerId,bankAccountDTO);
        return new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
    }

    @GetMapping("/get/{bankAccountId}")
    public ResponseEntity<BankAccountBalanceDTO> getBankAccountBalance(@PathVariable long bankAccountId) throws BankAccountNotFoundException {
        BankAccountBalanceDTO bankAccountBalanceDTO = bankAccountService.retrieveBalance(bankAccountId);
        return ResponseEntity.ok(bankAccountBalanceDTO);
    }


}
