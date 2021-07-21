package com.demo.bankingms.service;

import com.demo.bankingms.enums.Ordering;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Account> findAll(Ordering ordering){
        Ordering ordering1 = ordering;
        if (ordering1.name().equals(Sort.Direction.ASC.name())){
            return accountRepository.findAll(Sort.by(Sort.Direction.ASC, "creation"));
        }
        else {
            return accountRepository.findAll(Sort.by(Sort.Direction.DESC, "creation"));
        }
    }

    public int fetchAccount(Account account){
        Account existingAccount = accountRepository.findByNumberAndCode(account.getNumber(), account.getCode());
        if (existingAccount == null)
        {
           accountRepository.save(account);
           return account.getId();
        }
        else {
            account.setId(existingAccount.getId());
            accountRepository.save(account);
            return account.getId();
        }
    }

    public int createOrUpdate(Account account) {
        Instant instant = Instant.now();
        account.setCreation(LocalDateTime.now());
        return account.getId();
    }

    public Optional<Account> findAccountById(Integer id) {

        return accountRepository.findById(id.intValue());
    }

}
