package com.demo.bankingms.repository;

import com.demo.bankingms.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByNumberAndCode(String number, String code);
}
