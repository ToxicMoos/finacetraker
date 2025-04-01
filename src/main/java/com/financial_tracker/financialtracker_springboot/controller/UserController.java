package com.financial_tracker.financialtracker_springboot.controller;

import com.financial_tracker.financialtracker_springboot.dto.UserRegistration;
import com.financial_tracker.financialtracker_springboot.dto.UserResponse;
import com.financial_tracker.financialtracker_springboot.model.UserEntity;
import com.financial_tracker.financialtracker_springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserEntity> addUser(@Valid @RequestBody  UserEntity user) {
//        UserEntity createdUser =  userService.createUser(user);
//
//        return ResponseEntity.status(201).body(createdUser);
//    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRegistration user) {
        UserResponse createdUser =  userService.createUser(user);

        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping()
    public List<UserEntity> getAllUsers(){
       return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable  Long id) {
        UserEntity userEntity = userService.getUserById(id);
        return ResponseEntity.ok().body(userEntity);
    }
}
