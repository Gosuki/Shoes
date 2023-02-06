package com.example.web_giay.service;

import com.example.web_giay.dto.UserDTO;
import com.example.web_giay.entity.Users;

public interface UserService {
    Users signUp(UserDTO user);

    String verifyToken(String token);
    String deleteUser(Long[] ids);
}
