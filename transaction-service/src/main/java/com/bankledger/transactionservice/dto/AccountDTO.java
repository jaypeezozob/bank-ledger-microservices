package com.bankledger.transactionservice.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
