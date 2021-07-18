package com.demo.bankingms.service;

import com.demo.bankingms.model.Account;
import com.demo.bankingms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> fetchAccounts(){
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(t -> accountList.add(t));
        return accountList;
    }

    public Optional<Account> findAccount(Account account){
        return accountRepository.findById(account.getId());
    }

    public String fetchAccount(Account account){
        Account existingAccount = accountRepository.findByNumberAndCode(account.getNumber(), account.getCode());
        if (existingAccount == null)
        {
           accountRepository.save(account);
           return "Creating Account";
        }
        else {
            account.setId(existingAccount.getId());
            accountRepository.save(account);
            return "Updating Account";
        }
    }

    public Map<String, Account> createOrUpdate(Account account) {
        Map<String, Account> accountMap = new HashMap<String, Account>();
        String accountStatus = fetchAccount(account);

        accountMap.put(accountStatus, account);
        return accountMap;
    }
}
