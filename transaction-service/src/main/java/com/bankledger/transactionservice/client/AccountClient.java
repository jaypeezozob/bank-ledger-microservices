package com.bankledger.transactionservice.client;

import com.bankledger.transactionservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/accounts/{accountNumber}")
    AccountDTO getAccount(@PathVariable String accountNumber);

    @PutMapping("/accounts/{accountNumber}/debit")
    void debit(@PathVariable String accountNumber, @RequestParam BigDecimal amount);

    @PutMapping("/accounts/{accountNumber}/credit")
    void credit(@PathVariable String accountNumber, @RequestParam BigDecimal amount);
}
