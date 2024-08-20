package com.app.entities;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "training_session")
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSession {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionName;
    
    private LocalDateTime sessionDate;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToMany
    @JoinTable(
        name = "training_session_players",
        joinColumns = @JoinColumn(name = "training_session_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players = new ArrayList<>();

    // Getters, setters, and constructors
}
