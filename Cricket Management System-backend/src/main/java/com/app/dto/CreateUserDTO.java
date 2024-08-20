package com.app.dto;

import com.app.entities.Coach;
import com.app.entities.Player;
import com.app.entities.Role;
import com.app.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDTO {

	private String username;
	private String password;
    private Role role;
	private String email;
	private String phoneNumber;	
	private String playerPosition;
	private String playerTeam;
	private String playerBattingStyle;
	private String playerBowlingStyle;
	private String coachSpecialization;
	private String coachExperienceYears;
	
	
	
	
}
