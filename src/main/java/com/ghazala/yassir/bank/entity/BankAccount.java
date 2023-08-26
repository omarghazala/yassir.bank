package com.ghazala.yassir.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Double balance;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Customer customer;

    public BankAccount(Double balance,Customer customer){
        this.balance=balance;
        this.customer=customer;
    }



}
