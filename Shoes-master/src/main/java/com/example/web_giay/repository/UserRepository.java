package com.example.web_giay.repository;

import com.example.web_giay.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   Users findUsersByUsernameAndActive(String name,boolean active);
   Users findUsersById(Long id);
}
