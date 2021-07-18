package com.demo.bankingms.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String number;
    @Column
    private String code;
    @Column
    private Double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + ": " +
                "Account Number='" + number + '\'' +
                ", Account Code ='" + code + '\'' +
                ", Account Balance=" + balance;
    }
}
