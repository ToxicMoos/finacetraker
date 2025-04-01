package com.financial_tracker.financialtracker_springboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {

    private Double amount;      // Сумма транзакции
    private String type;        // Тип: "income" (доход) или "expense" (расход)
    private String category;    // Категория (например, "еда", "зарплата")
    private String description; // Описание
    private Long userId;
}
