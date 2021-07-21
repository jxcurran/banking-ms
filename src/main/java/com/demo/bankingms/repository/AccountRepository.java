package com.demo.bankingms.repository;

import com.demo.bankingms.enums.Ordering;
import com.demo.bankingms.model.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByNumberAndCode(String number, String code);

}
