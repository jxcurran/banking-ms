package com.demo.bankingms.repository;
import com.demo.bankingms.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findBySenderOrReceiver(String settlement, String collection);
}
