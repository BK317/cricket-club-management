package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ResponseTeamDTO;
import com.app.dto.TeamDTO;
import com.app.entities.Coach;
import com.app.entities.Player;
import com.app.entities.Team;
import com.app.repository.CoachRepository;
import com.app.repository.PlayerRepository;
import com.app.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	
	
	  @Autowired
	    private TeamRepository teamRepository;
	  
	  
	  @Autowired
	  private CoachRepository cRep;
	  
	  @Autowired
	  private PlayerRepository pRep;

	    @Autowired
	    private ModelMapper modelMapper;
	    
	    
	    private ResponseTeamDTO mapteamtoteamDto(Team t) {
	    	ResponseTeamDTO tDTO =new ResponseTeamDTO();
	    	tDTO.setId(t.getId());
	    	tDTO.setCoach(t.getCoach().getId());
	    	tDTO.setName(t.getName());
	    	tDTO.setCoachname(t.getCoach().getUser().getUsername());
	    	tDTO.setPlayers(t.getCaptain().getId());
	    	tDTO.setPlayerName(t.getCaptain().getUser().getUsername());
	    	
	    	return tDTO;
	    	
	    	
	    }

	    @Override
		public List<ResponseTeamDTO> getAllTeams() {
	        List<Team> teams = teamRepository.findAll();
	        List<ResponseTeamDTO> t=new ArrayList<ResponseTeamDTO>();
	        ResponseTeamDTO r=new ResponseTeamDTO();
             for (Team team : teams) {
            	t.add(mapteamtoteamDto(team));
				
			}
             
             return t;

	    }
	    
	    
	    @Override
	    public ResponseTeamDTO getTeamById(Long id) {
	        Team t= teamRepository.findById(id).orElseThrow();
	        
	        return mapteamtoteamDto(t);
	    }

	    @Override
	    public ResponseTeamDTO createTeam(ResponseTeamDTO teamDTO) {
	    	
	    Coach c=cRep.findById(teamDTO.getCoach()).orElseThrow();
	    Player p=pRep.findById(teamDTO.getPlayers()).orElseThrow();
	        Team team = modelMapper.map(teamDTO, Team.class);
	        team.setCoach(c);
	        team.setCaptain(p);
	        Team savedTeam = teamRepository.save(team);
	        return mapteamtoteamDto(savedTeam);
	    }

	    @Override
	    public ResponseTeamDTO updateTeam(Long id, ResponseTeamDTO teamDTO) {
	       Team t=teamRepository.findById(id).orElseThrow();
	       Coach c=cRep.findById(teamDTO.getCoach()).orElseThrow();
	       Player p=pRep.findById(teamDTO.getPlayers()).orElseThrow();
	       t.setName(teamDTO.getName());
	       t.setCoach(c);
	       t.setCaptain(p);
	       Team t1= teamRepository.save(t);
	        return mapteamtoteamDto(t1);
	    }

	    @Override
	    public void deleteTeam(Long id) {
	        teamRepository.deleteById(id);
	    }

}
