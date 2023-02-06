package com.example.web_giay.dto;

import lombok.Data;

@Data
public class SingIn {
    private String usernameOrEmail;
    private String password;
}
