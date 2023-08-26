package com.ghazala.yassir.bank.mapper;

import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {

    private static TransactionDTO transactionToTransactionDTO(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getAmount(), transaction.getSourceBankAccount().getId(),transaction.getTargetBankAccount().getId());
        return transactionDTO;
    }

    public static List<TransactionDTO> transactionListToTransactionDTOList(List<Transaction> transactionList){

        List<TransactionDTO> transactionDTOArrayList= new ArrayList<>();
        for(Transaction transaction:transactionList){
            transactionDTOArrayList.add(TransactionMapper.transactionToTransactionDTO(transaction));
        }
        return transactionDTOArrayList;
    }
}
