package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

 

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String email;
    
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private Player player;

    @OneToOne(mappedBy = "user")
    private Coach coach;
}

