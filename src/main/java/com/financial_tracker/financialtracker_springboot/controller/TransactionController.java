package com.financial_tracker.financialtracker_springboot.controller;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.service.TransactionService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {



    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<MyTransaction> addTransaction(@RequestBody MyTransaction transaction) {
        if (transaction == null || transaction.getAmount() < 0) {
            throw new IllegalArgumentException("Данные транзакции некорректны ");
        }
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<MyTransaction> getAllTransAction() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    public MyTransaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
    }
}
