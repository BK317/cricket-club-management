package com.app.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coaches")
@Data
@NoArgsConstructor
public class Coach extends BaseEntity {

    

    private String name;
    private String specialization;
    private int experienceYears;
    
    
    @Enumerated(EnumType.STRING)
    
    private AccountStatus accountStatus;



    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "coach")
    private Team team;
    
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<TrainingSession> trainingSessions = new ArrayList<>();
}

