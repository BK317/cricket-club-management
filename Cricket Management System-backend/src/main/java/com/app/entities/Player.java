package com.app.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String position;

    @ManyToOne
    private Team teamId;

    @Column(name = "batting_style")
    private String battingStyle;

    @Column(name = "bowling_style")
    private String bowlingStyle;

    @Column(name = "is_approved")
    
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    
    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<TrainingSession> trainingSessions = new ArrayList();
  
    
    @OneToOne
    private User user;
}
