package com.demo.bankingms.data;


public class TransactionData {

    private String accountNumber;

    private String accountSortCode;

    private String counterpartyNumber;

    private String counterpartySortCode;

    private Double amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountSortCode() {
        return accountSortCode;
    }

    public void setAccountSortCode(String accountSortCode) {
        this.accountSortCode = accountSortCode;
    }

    public String getCounterpartyNumber() {
        return counterpartyNumber;
    }

    public void setCounterpartyNumber(String counterpartyNumber) {
        this.counterpartyNumber = counterpartyNumber;
    }

    public String getCounterpartySortCode() {
        return counterpartySortCode;
    }

    public void setCounterpartySortCode(String counterpartySortCode) {
        this.counterpartySortCode = counterpartySortCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}