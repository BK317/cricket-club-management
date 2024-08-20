package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTeamDTO {
	
	 private Long id;
	    private String name;
	    private Long coach;
	    private String coachname;
	    private Long players;
	    private String playerName;

}
