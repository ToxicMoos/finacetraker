package com.financial_tracker.financialtracker_springboot.dto;


import lombok.Data;

@Data
public class UserRegistration {

    private String userName;
    private String email;
    private String password;

}
