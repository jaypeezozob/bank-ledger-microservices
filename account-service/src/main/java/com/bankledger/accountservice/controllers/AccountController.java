package com.bankledger.accountservice.controllers;

import com.bankledger.accountservice.entities.Account;
import com.bankledger.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{accountNumber}")
    public Optional<Account> getAccount(@PathVariable String accountNumber){
        return accountService.getAccount(accountNumber);
    }

    @PutMapping("/{accountNumber}/debit")
    public Account debit(@PathVariable String accountNumber,
                         @RequestParam BigDecimal amount) {
        return accountService.debit(accountNumber, amount);
    }

    @PutMapping("/{accountNumber}/credit")
    public Account credit(@PathVariable String accountNumber,
                          @RequestParam BigDecimal amount) {
        return accountService.credit(accountNumber, amount);
    }
}

