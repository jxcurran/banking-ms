package com.demo.bankingms.controller;

import com.demo.bankingms.data.AccountTransactions;
import com.demo.bankingms.data.AccountTransactionsResponseObject;
import com.demo.bankingms.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Simple rest controller to expose endpoints to fetch account transaction information
@RestController
public class AccountTransactionsController {

    @Autowired
    AccountTransactionService accountTransactionService;

    @PostMapping("/account-transactions")
    private AccountTransactionsResponseObject fetchTransactionsOnAccount(@RequestBody AccountTransactions account){
        return accountTransactionService.fetchTransactionsByAccount(account);
    }
}
