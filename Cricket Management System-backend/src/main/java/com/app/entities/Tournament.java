package com.app.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Data
@NoArgsConstructor
public class Tournament extends BaseEntity {


    private String name;

    
    private LocalDate  startDate;

   
    private LocalDate  endDate;

    private String location;

    @OneToMany(mappedBy = "tournament")
    private List<Match> matches = new ArrayList<>();
}
