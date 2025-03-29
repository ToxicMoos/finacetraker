package com.financial_tracker.financialtracker_springboot.service;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);     //TransactionService.class

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<MyTransaction> createTransaction(MyTransaction myTransaction) {
        transactionRepository.save(myTransaction);
        return ResponseEntity.ok().body(myTransaction);
    }

    public List<MyTransaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public MyTransaction getTransactionById(Long id) {
        if (id == null || id == 0) {
            logger.error("ID или данные для обновления не могут быть null:" + id);
            throw new IllegalArgumentException("ID не может быть null");
        }
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Транзакция с ID " + id + " не найдена"));
    }


    @Transactional
    public MyTransaction updateTransaction(Long id, MyTransaction updatedTransaction) {

        validated(id);
        MyTransaction myTransaction = transactionRepository
                .findById(id).orElseThrow(() -> {
                    logger.warn("Транзакция с ID {} не найдена", id);
                    return new EntityNotFoundException("Транзакция с ID {} " + id + "не найдена ");
                });
        myTransaction.setAmount(updatedTransaction.getAmount());
        myTransaction.setDate(updatedTransaction.getDate());
        myTransaction.setType(updatedTransaction.getType());
        myTransaction.setCategory(updatedTransaction.getCategory());
        myTransaction.setDescription(updatedTransaction.getDescription());

        return transactionRepository.save(myTransaction);
    }

    public void deleteTransactionById(Long id) {
        validated(id);
        transactionRepository.deleteById(id);
    }

    public void validated(Long id) {
        logger.error("ID или данные для обновления не могут быть null или 0: " + id);
        throw new IllegalArgumentException("ID не может быть null or 0");
    }

}
