package com.ghazala.yassir.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDTO {
    double amount;
    Long sourceBankAccountId;
    Long targetBankAccountId;
}
