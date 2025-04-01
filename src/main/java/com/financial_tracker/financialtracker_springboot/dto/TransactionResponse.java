package com.financial_tracker.financialtracker_springboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionResponse {

    private Long id;
    private Double amount;      // Сумма транзакции
    private LocalDate date;     // Дата транзакции
    private String type;        // Тип: "income" (доход) или "expense" (расход)
    private String category;    // Категория (например, "еда", "зарплата")
    private String description; // Описание

    private  UserResponse userResponse;
}
