package com.app.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
public class Match extends BaseEntity {

    

   
    private LocalDate date;

    private String type;
    private String result;
    
    private String teamAScore;

    private String teamBScore;

    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    private Team teamB;
    
    
    
}
