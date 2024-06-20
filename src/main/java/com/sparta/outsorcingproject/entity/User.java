package com.sparta.outsorcingproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String introduce; // intro -> introduce

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime statusChangedAt;

    // Refresh Token을 저장할 필드
//    @Column
//    private String refreshToken;

    public User(String username, String password, String email, String introduce, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.role = role;
    }

}
