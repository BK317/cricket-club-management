package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
	
	 private Long id;
	    private String name;
	    private String position;
	    private String team;
	    private String battingStyle;
	    private String bowlingStyle;
	    private boolean isApproved;

}
