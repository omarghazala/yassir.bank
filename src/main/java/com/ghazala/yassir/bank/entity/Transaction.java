package com.ghazala.yassir.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Double amount;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "source_bank_account_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    BankAccount sourceBankAccount;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "target_bank_account_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    BankAccount targetBankAccount;


}
