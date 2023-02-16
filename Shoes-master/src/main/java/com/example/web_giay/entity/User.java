package com.example.web_giay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String displayName;
    private String username;
    private String email;
    private String phone;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    private boolean active;
}
