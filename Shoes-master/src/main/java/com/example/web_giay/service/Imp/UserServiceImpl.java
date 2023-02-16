package com.example.web_giay.service.Imp;

import com.example.web_giay.dto.UserDTO;
import com.example.web_giay.entity.CustomUserDetail;
import com.example.web_giay.entity.SignUpToken;
import com.example.web_giay.entity.User;
import com.example.web_giay.repository.TokenRepository;
import com.example.web_giay.repository.UserRepository;
import com.example.web_giay.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final TokenRepository tokenRepository;


    @Override
    @Transactional
    public User signUp(UserDTO userDTO) {
        logger.info("On sign up");
        User user = new User();
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setDisplayName(userDTO.getFullName());
        user.setActive(false);
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        User savedUser = userRepository.save(user);
        generateToken(savedUser);
        return savedUser;
    }

    @Override
    public String verifyToken(String token) {
        Optional<SignUpToken> optionalSignUpToken = tokenRepository.findSignUpTokenByToken(token);
        if (optionalSignUpToken.isEmpty()) {
            return "Activate failed";
        }
        SignUpToken signUpToken = optionalSignUpToken.get();
        User user = signUpToken.getUser();
        user.setActive(true);
        userRepository.save(user);
        return "Activate successfully";
    }

    @Override
    public String deleteUser(Long[] ids) {
        for(Long item:ids){
            if(userRepository.findUserById(item)!=null){
                userRepository.deleteById(item);
            } else {
                return "Not found userid";
            }
        }
        return "Delete Successful";
    }


    private void generateToken(User user) {
        UUID token = UUID.randomUUID();
        SignUpToken signUpToken = new SignUpToken();
        signUpToken.setUser(user);
        signUpToken.setToken(String.valueOf(token));
        tokenRepository.save(signUpToken);

        //send email
        try {
            emailService.sendEmail(user.getEmail(), "Please active your account", "localhost:8081/user/verify/" + token);
        } catch (Exception e) {
            logger.error("Send email failed");
            throw e;
        }
        logger.info("Send email activate successfully");
    }

}
