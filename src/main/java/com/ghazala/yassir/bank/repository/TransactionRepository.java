package com.ghazala.yassir.bank.repository;

import com.ghazala.yassir.bank.dto.TransactionDTO;
import com.ghazala.yassir.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllBySourceBankAccountIdOrTargetBankAccountId(Long sourceBankAccountId,Long targetBankAccountId);
}
