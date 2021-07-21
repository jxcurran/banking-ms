package com.demo.bankingms.service;
import com.demo.bankingms.data.TransactionData;
import com.demo.bankingms.model.Account;
import com.demo.bankingms.model.Transactions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    private Double startingBalance = 100.00;

    private Double transferAmount = 50.00;


    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_andCheckBalances() {

        TransactionData transactionData = new TransactionData();
        Account account1 = new Account();
        Account account2 = new Account();
        createAccountData(account1, account2);
        createTransactionData(transactionData);
        Map<String,Transactions> transactionsMap = transactionService.createTransaction(transactionData);
        Assert.assertNotNull(transactionsMap);
        Transactions createdTransaction = transactionsMap.get("Success");
        Assert.assertNotNull(createdTransaction);
        Assert.assertEquals(createdTransaction.getAmount(), transferAmount);

        Optional<Account> updatedAccount1 = accountService.findAccount(account1);
        Account fetchedAccount = updatedAccount1.get();
        assertEquals(fetchedAccount.getBalance().doubleValue(), startingBalance.doubleValue() - transferAmount.doubleValue());

        Optional<Account> updatedAccount2 = accountService.findAccount(account2);
        Account fetchedAccount2 = updatedAccount2.get();
        assertEquals(fetchedAccount2.getBalance().doubleValue(), startingBalance.doubleValue() + transferAmount.doubleValue());
        removeData(account1,account2, createdTransaction);

    }

    private void removeData(Account account1, Account account2, Transactions createdTransaction) {
        accountService.accountRepository.delete(account1);
        accountService.accountRepository.delete(account2);
        transactionService.transactionRepository.delete(createdTransaction);
    }

    private void createAccountData(Account account1, Account account2){
        account1.setNumber("1");
        account1.setBalance(startingBalance);
        account1.setName("name1");
        account1.setCode("code1");
        account1.setCreation(LocalDateTime.now().plusYears(1));
        accountService.createOrUpdate(account1);
        account2.setNumber("2");
        account2.setBalance(startingBalance);
        account2.setName("name2");
        account2.setCode("code2");
        account2.setCreation(LocalDateTime.now().minusYears(1));
        accountService.createOrUpdate(account2);
    }



    private void createTransactionData(TransactionData transactionData) {
        transactionData.setAmount(transferAmount);
        transactionData.setAccountNumber("1");
        transactionData.setAccountSortCode("code1");
        transactionData.setCounterpartyNumber("2");
        transactionData.setCounterpartySortCode("code2");

    }


}