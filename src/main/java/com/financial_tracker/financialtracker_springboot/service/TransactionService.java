package com.financial_tracker.financialtracker_springboot.service;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.repository.TransactionRepository;
import com.financial_tracker.financialtracker_springboot.validation.Validation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final Validation validate;

    public ResponseEntity<MyTransaction> createTransaction(MyTransaction myTransaction) {
        validate.validateTransaction(myTransaction);
        MyTransaction savedTransaction = transactionRepository.save(myTransaction);
        return ResponseEntity.status(201).body(savedTransaction);
    }

    public List<MyTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public MyTransaction getTransactionById(Long id) {
        validate.validateId(id);
        return transactionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Транзакция с ID " + id + " не найдена"));
    }

    public MyTransaction updateTransaction(Long id, MyTransaction updatedTransaction) {
        validate.validateId(id);
        validate.validateTransaction(updatedTransaction);
        MyTransaction myTransaction = transactionRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Транзакция с ID " + id + " не найдена "));

        if (updatedTransaction.getAmount() != null) {
            myTransaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getDate() != null) {
            myTransaction.setDate(updatedTransaction.getDate());
        }
        if (updatedTransaction.getType() != null) {
            myTransaction.setType(updatedTransaction.getType());
        }
        if (updatedTransaction.getCategory() != null) {
            myTransaction.setCategory(updatedTransaction.getCategory());
        }
        if (updatedTransaction.getDescription() != null) {
            myTransaction.setDescription(updatedTransaction.getDescription());
        }

        return transactionRepository.save(myTransaction);
    }

    public void deleteTransactionById(Long id) {
        validate.validateId(id);
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException(" Транзакция с ID " + id + " не найдена ");
        }
        transactionRepository.deleteById(id);
    }

}
