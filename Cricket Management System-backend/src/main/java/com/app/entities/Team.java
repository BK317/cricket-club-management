package com.app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
public class Team extends BaseEntity{





	    @Column(nullable = false, unique = true)
	    private String name;

	    @ManyToOne
	    @JoinColumn(name = "coach_id")
	    private Coach coach; // Assuming you have a Coach entity
	



	    @OneToMany(mappedBy = "teamId")
	    private Set<Player> players;


	    

	 
	    @OneToOne
	    private Player captain; // Or you can use a Player entity reference

	    @Column(name = "team_logo")
	    private String teamLogo;
	    
	    
	   

	   
	}


