package com.demo.bankingms.controller;

import com.demo.bankingms.enums.Ordering;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.service.AccountService;
import com.demo.bankingms.service.TransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/account")
    @ResponseBody
    private Account fetchAccountById(@RequestParam String id){
        int foo = Integer.valueOf(id);
        return accountService.findAccountById(foo).get();
    }

    @GetMapping("/accountOrdering")
    @ResponseBody
    private List<Account> fetchAccountByOrder(@RequestParam(defaultValue = "DESC") String Order){
        Ordering ordering1 = Ordering.valueOf(Order.toUpperCase());
        return accountService.findAll(ordering1);
    }



//    @GetMapping("/accountOrdering")
//    @ResponseBody
//    private ResponseEntity<String> fetchAccountByOrder(@RequestParam String Order){
//        try {
//            Ordering ordering1 = Ordering.valueOf(Order.toUpperCase());
//            GsonBuilder gson = new GsonBuilder();
//            String response = gson.create().toJson(accountService.findAllOrderBy(), Account.class);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(e.toString(), HttpStatus.OK);
//        }
//    }

    @PostMapping("/account-create")
    private int createOrUpdate(@RequestBody Account account){
      return accountService.createOrUpdate(account);
    }

}
