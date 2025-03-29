package com.financial_tracker.financialtracker_springboot.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor


public class MyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;      // Сумма транзакции
    private LocalDate date;     // Дата транзакции
    private String type;        // Тип: "income" (доход) или "expense" (расход)
    private String category;    // Категория (например, "еда", "зарплата")
    private String description; // Описание



    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
