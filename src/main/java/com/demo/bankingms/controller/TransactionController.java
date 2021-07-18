package com.demo.bankingms.controller;
import com.demo.bankingms.data.TransactionData;
import com.demo.bankingms.model.Transactions;
import com.demo.bankingms.service.TransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactions")
    private List<Transactions> fetchTransactions() {
        return transactionService.fetchTransactions();
    }

    @PostMapping("/createTransaction")
    private Map<String, Transactions> createTransaction(@RequestBody TransactionData transaction) {
        return transactionService.createTransaction(transaction);
    }


    @PostMapping("/createTransactionResponse")
    private ResponseEntity<String> createTransactionResponse(@RequestBody TransactionData transactionData){
        try {
            Transactions transaction = transactionService.createTransactionResponse(transactionData);
            String jsonString = new GsonBuilder().create().toJson(transaction, Transactions.class);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Transaction Service Exception" + e.getMessage(), HttpStatus.OK);
        }
    }

}
