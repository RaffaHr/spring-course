package com.raffadev.springcourse.model;

import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    public UserEntity() {};

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }
}
