package com.app.dto;


import com.app.entities.AccountStatus;
import com.app.entities.Team;
import com.app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTOAdmin {
    private Long id;
    private String name;
    private String position;
    private Long teamId; // Assuming teamId is referenced by its ID
    private String battingStyle;
    private String bowlingStyle;
    private AccountStatus accountStatus;
    private Long userId; // Assuming userId is referenced by its ID
}