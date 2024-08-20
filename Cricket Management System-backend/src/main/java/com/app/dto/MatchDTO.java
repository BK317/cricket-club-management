package com.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MatchDTO {
    private Long id;
    private LocalDate date;
    private String type;
    private String result;
    private String teamAScore;
    private String teamBScore;
    private Long groundId;
    private Long tournamentId;
    private Long teamAId;
    private Long teamBId;
}