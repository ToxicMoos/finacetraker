package com.financial_tracker.financialtracker_springboot.validation;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class Validation {
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public <T extends Number> void validateId(T id) {
        if (id == null || id.longValue() < 1) {
            logger.error("Некорректный ID: " + id);
            throw new IllegalArgumentException("ID не может быть null или меньше 1");
        }
    }

    public <T> void validateTransaction(MyTransaction myTransaction) {
        if (myTransaction == null) {
            throw new IllegalArgumentException("Данные транзакции не могут быть null (Пустыми)");
        }
        if (myTransaction.getAmount() < 0) {
            throw new IllegalArgumentException("Сумма транзакции должна быть положительной");
        }
    }

}
