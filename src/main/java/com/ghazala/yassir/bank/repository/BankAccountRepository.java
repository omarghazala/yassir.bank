package com.ghazala.yassir.bank.repository;

import com.ghazala.yassir.bank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    BankAccount findByCustomerId(Long customerId);
}
