package com.demo.bankingms.controller;

import com.demo.bankingms.data.AccountTransactions;
import com.demo.bankingms.data.AccountTransactionsResponseObject;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.model.Transactions;
import com.demo.bankingms.service.AccountService;
import com.demo.bankingms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Simple rest controller to expose endpoints to fetch account information and create a bank account.
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    private List<Account> fetchAllAccounts(){
        return accountService.fetchAccounts();
    }

    @PostMapping("/account-create")
    private Map<String,Account> createOrUpdate(@RequestBody Account account){
      return accountService.createOrUpdate(account);
    }

}
