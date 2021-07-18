package com.demo.bankingms.data;

import com.demo.bankingms.model.Account;
import com.demo.bankingms.model.Transactions;

import java.util.List;

public class AccountTransactionsResponseObject {
    private Account account;
    private List<Transactions> transactionsList;


    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
