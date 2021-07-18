package com.demo.bankingms.service;

import com.demo.bankingms.data.AccountTransactions;
import com.demo.bankingms.data.AccountTransactionsResponseObject;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.model.Transactions;
import com.demo.bankingms.repository.AccountRepository;
import com.demo.bankingms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AccountTransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public AccountTransactionsResponseObject fetchTransactionsByAccount(AccountTransactions accountTransactions){
        List<Transactions> accountTransactionsList = null;
        Map<Account, List<Transactions>> accountDetailsMap = new HashMap<>();
        Account account = accountRepository.findByNumberAndCode(accountTransactions.getAccountNumber(), accountTransactions.getAccountCode());
        if (account != null){
            accountTransactionsList = transactionRepository.findBySenderOrReceiver(account.getNumber(), account.getNumber());
            accountDetailsMap.put(account, accountTransactionsList);
        } else {
            accountDetailsMap.put(null, accountTransactionsList);
        }
        return maptoResponseObject(accountDetailsMap);

    }

    private AccountTransactionsResponseObject maptoResponseObject(Map<Account, List<Transactions>> accountDetailsMap) {
        AccountTransactionsResponseObject accountTransactionsResponseObject = new AccountTransactionsResponseObject();
        accountTransactionsResponseObject.setAccount(accountDetailsMap.entrySet().iterator().next().getKey());
        accountTransactionsResponseObject.setTransactionsList(accountDetailsMap.entrySet().iterator().next().getValue());
        return accountTransactionsResponseObject;

    }

}
