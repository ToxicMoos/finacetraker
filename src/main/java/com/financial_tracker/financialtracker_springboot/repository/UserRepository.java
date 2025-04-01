package com.financial_tracker.financialtracker_springboot.repository;

import com.financial_tracker.financialtracker_springboot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
