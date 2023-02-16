package com.example.web_giay.repository;

import com.example.web_giay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findUsersByUsernameAndActive(String name, boolean active);
   User findUserById(Long id);
}
