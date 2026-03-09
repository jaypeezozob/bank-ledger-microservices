package com.bankledger.transactionservice.services;

import com.bankledger.transactionservice.Entities.Transaction;
import com.bankledger.transactionservice.client.AccountClient;
import com.bankledger.transactionservice.dto.AccountDTO;
import com.bankledger.transactionservice.respositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountClient accountClient;

    public Transaction createTransaction(String accountNumber, BigDecimal amount, String type) {

        AccountDTO account = accountClient.getAccount(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        // Validate balance for debit
        if ("DEBIT".equalsIgnoreCase(type) && account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insuficient balance");
        }

        // Call Account Service to update balance
        if ("DEBIT".equalsIgnoreCase(type)) {
            accountClient.debit(accountNumber, amount);
        } else if ("CREDIT".equalsIgnoreCase(type)) {
            accountClient.credit(accountNumber, amount);
        }

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

}
