package com.example.web_giay.dto;

import com.example.web_giay.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @NotEmpty(message = "Thieu fullName")
    private String fullName;
    @NotEmpty(message = "thieu user")
    private String username;
    @Email(message = "Email khong hop le")
    private String email;
    @NotEmpty(message = "thieu so phone")
    @Min(value = 9,message = "so phone 9 ki tu tro len")
    private String phone;
    @NotEmpty(message = "thieu password")
    @Min(value = 8,message = "password 8 ki tu tro len")
    private String password;
    private Role role;
    private boolean active;
}
