package com.demo.bankingms.service;

import com.demo.bankingms.data.AccountTransactions;
import com.demo.bankingms.data.AccountTransactionsResponseObject;
import com.demo.bankingms.data.TransactionData;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.model.Transactions;
import com.demo.bankingms.repository.AccountRepository;
import com.demo.bankingms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Transactions> fetchTransactions() {
        List<Transactions> transactionList = new ArrayList<>();
        transactionRepository.findAll().forEach(c -> transactionList.add(c));
        return transactionList;
    }

    /*
       Creates transactions by matching the transaction request to an existing request
       If the accounts and available balances are valid
       We store the transaction and update the account balances
     */
    public Map<String,Transactions> createTransaction(TransactionData transaction) {
        String transactionStatus = "Transaction Failed";
        Transactions transactionEntity = new Transactions();
        boolean validTransaction = false;
        if (validateTransactionFields(transaction)){
            Map.Entry<Account,Account> entry = getAccounts(transaction);
            Account account = entry.getKey();
            Account counterparty = entry.getValue();
            if (account == null || counterparty == null){
                transactionStatus = "Account(s) Not Found, Please Verify numbers and sort codes";
            }
            else {
                Double transferAmount = transaction.getAmount();
                validTransaction = canTransfer(account, transferAmount);
                if (!validTransaction){
                    transactionStatus = "Insufficient Balance: Account Balance: " + account.getBalance() + ", Transfer Amount: " + transaction.getAmount();
                    populateTransaction(transactionEntity, account, counterparty, 0.00);
                }
                else {
                    counterparty.setBalance(counterparty.getBalance() + transferAmount);
                    account.setBalance(account.getBalance() - transferAmount);
                    populateTransaction(transactionEntity, account, counterparty, transferAmount);
                    transactionRepository.save(transactionEntity);
                    accountRepository.save(account);
                    accountRepository.save(counterparty);
                    transactionStatus = "Success";
                }
            }
        }
        else {
            transactionStatus = "Invalid Transaction Fields";
        }
        Map<String, Transactions> transactionMap = new HashMap<>();
        transactionMap.put(transactionStatus, transactionEntity);
        return transactionMap;
    }

    private void populateTransaction(Transactions transactionEntity, Account account, Account counterparty, Double transferAmount) {
        transactionEntity.setAmount(transferAmount);
        transactionEntity.setReceiver(counterparty.getNumber());
        transactionEntity.setSender(account.getNumber());
    }

    private Map.Entry<Account,Account> getAccounts(TransactionData transaction) {
        Map<Account, Account> accounts = fetchAccounts(transaction);
        Map.Entry<Account,Account> entry = accounts.entrySet().iterator().next();
        return entry;
    }

    private boolean canTransfer(Account account, Double amount){
        return account.getBalance() < amount ? false : true;
    }

    private Map<Account, Account> fetchAccounts(TransactionData transactionData) {
       Map<Account, Account> accountMap = new HashMap<>();
       Account account = accountRepository.findByNumberAndCode(transactionData.getAccountNumber(), transactionData.getAccountSortCode());
       Account counterParty = accountRepository.findByNumberAndCode(transactionData.getCounterpartyNumber(), transactionData.getCounterpartySortCode());
       accountMap.put(account, counterParty);
       return accountMap;
    }

    private boolean validateTransactionFields(TransactionData transactionData) {
        boolean validTransaction;
        if (transactionData != null){
           validTransaction = stringValidator(transactionData.getAccountNumber(), true);
           validTransaction = stringValidator(transactionData.getAccountSortCode(), validTransaction);
           validTransaction = stringValidator(transactionData.getCounterpartyNumber(), validTransaction);
           validTransaction = stringValidator(transactionData.getCounterpartySortCode(), validTransaction);
           validTransaction = transactionData.getAmount() != null && validTransaction ? (transactionData.getAmount() < 0 ? false : true) : false;
        }
        else {
            return false;
        }
        return validTransaction;
    }

    private boolean stringValidator(String a, Boolean validTransaction){
        return a != null && validTransaction;
    }

    public Transactions createTransactionResponse(TransactionData transaction) {
        String transactionStatus = "Transaction Failed";
        Transactions transactionEntity = new Transactions();
        boolean validTransaction = false;
        if (validateTransactionFields(transaction)){
            Map.Entry<Account,Account> entry = getAccounts(transaction);
            Account account = entry.getKey();
            Account counterparty = entry.getValue();
            if (account == null || counterparty == null){
                transactionStatus = "Account(s) Not Found, Please Verify numbers and sort codes";
            }
            else {
                Double transferAmount = transaction.getAmount();
                validTransaction = canTransfer(account, transferAmount);
                if (!validTransaction){
                    transactionStatus = "Insufficient Balance: Account Balance: " + account.getBalance() + ", Transfer Amount: " + transaction.getAmount();
                    populateTransaction(transactionEntity, account, counterparty, 0.00);
                }
                else {
                    counterparty.setBalance(counterparty.getBalance() + transferAmount);
                    account.setBalance(account.getBalance() - transferAmount);
                    populateTransaction(transactionEntity, account, counterparty, transferAmount);
                    transactionRepository.save(transactionEntity);
                    accountRepository.save(account);
                    accountRepository.save(counterparty);
                    transactionStatus = "Success";
                }
            }
        }
        else {
            transactionStatus = "Invalid Transaction Fields";
        }
        Map<String, Transactions> transactionMap = new HashMap<>();
        transactionMap.put(transactionStatus, transactionEntity);
        return transactionEntity;

    }
}
