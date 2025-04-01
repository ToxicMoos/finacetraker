package com.financial_tracker.financialtracker_springboot.service;

import com.financial_tracker.financialtracker_springboot.dto.UserRegistration;
import com.financial_tracker.financialtracker_springboot.dto.UserResponse;
import com.financial_tracker.financialtracker_springboot.model.UserEntity;
import com.financial_tracker.financialtracker_springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRegistration userRegistration) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userRegistration.getUserName());
        userEntity.setEmail(userRegistration.getEmail());
        userEntity.setPassword(userRegistration.getPassword());
        userEntity.setBalance(BigDecimal.ZERO);

        UserEntity savedUser = userRepository.save(userEntity);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setUserName(savedUser.getUserName());
        userResponse.setEmail(savedUser.getEmail());

        return userResponse;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
