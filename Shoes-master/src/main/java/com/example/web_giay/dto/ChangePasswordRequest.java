package com.example.web_giay.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String password;
    private String newPassword;
    private String confirmNewPassword;
}
