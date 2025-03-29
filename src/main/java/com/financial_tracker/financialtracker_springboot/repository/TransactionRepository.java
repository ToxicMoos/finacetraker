package com.financial_tracker.financialtracker_springboot.repository;

import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<MyTransaction, Long> {
}
