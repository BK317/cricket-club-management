package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "performances")
@Data
@NoArgsConstructor
public class Performance extends BaseEntity {


    private int runs;
    private int wickets;
    private int catches;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
