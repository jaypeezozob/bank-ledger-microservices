package com.bankledger.accountservice.services;

import com.bankledger.accountservice.entities.Account;
import com.bankledger.accountservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public Account debit(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account not found."));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        return accountRepository.save(account);
    }

    @Transactional
    public Account credit(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account not found."));

        account.setBalance(account.getBalance().add(amount));

        return accountRepository.save(account);
    }
}