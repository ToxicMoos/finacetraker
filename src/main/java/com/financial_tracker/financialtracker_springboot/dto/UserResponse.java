package com.financial_tracker.financialtracker_springboot.dto;


import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String userName;
    private String email;

}
