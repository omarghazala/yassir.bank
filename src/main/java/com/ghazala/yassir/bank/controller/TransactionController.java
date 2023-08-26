package com.ghazala.yassir.bank.controller;

import com.ghazala.yassir.bank.dto.BankAccountDTO;
import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.exceptions.BankAccountNotFoundException;
import com.ghazala.yassir.bank.exceptions.CustomerNotFoundException;
import com.ghazala.yassir.bank.exceptions.InsufficientFundsException;
import com.ghazala.yassir.bank.exceptions.NegativeFundsException;
import com.ghazala.yassir.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferAmount(@RequestBody TransactionDTO transactionDTO) throws BankAccountNotFoundException, InsufficientFundsException, NegativeFundsException {
        TransactionDTO createdTransaction = transactionService.transferAmount(transactionDTO);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/get/{bankAccountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable Long bankAccountId) throws BankAccountNotFoundException, InsufficientFundsException {
        List<TransactionDTO> transactionDTOList = transactionService.getTransactionHistory(bankAccountId);
        return ResponseEntity.ok(transactionDTOList);
    }

}
