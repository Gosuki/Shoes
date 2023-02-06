package com.example.web_giay.repository;

import com.example.web_giay.entity.SignUpToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<SignUpToken, Long> {

    Optional<SignUpToken> findSignUpTokenByToken(String token);
}
