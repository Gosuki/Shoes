package com.example.web_giay.service;

import com.example.web_giay.dto.UserDTO;
import com.example.web_giay.entity.User;

public interface UserService {
    User signUp(UserDTO user);

    String verifyToken(String token);
    String deleteUser(Long[] ids);
}
