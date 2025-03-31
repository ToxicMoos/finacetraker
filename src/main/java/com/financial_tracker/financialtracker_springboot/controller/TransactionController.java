package com.financial_tracker.financialtracker_springboot.controller;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<MyTransaction> addTransaction(@Valid @RequestBody  MyTransaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<MyTransaction> getAllTransActions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyTransaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyTransaction> updateTransactionById(@PathVariable Long id,
                                                               @Valid @RequestBody MyTransaction transaction){
        MyTransaction updatedTransaction = transactionService.updateTransaction(id,transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.status(204).build();
    }
}
