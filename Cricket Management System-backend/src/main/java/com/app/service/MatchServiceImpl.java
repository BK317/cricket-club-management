// src/main/java/com/app/service/MatchService.java

package com.app.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.MatchDTO;
import com.app.entities.Match;
import com.app.entities.Team;
import com.app.repository.GroundRepository;
import com.app.repository.MatchRepository;
import com.app.repository.TeamRepository;
import com.app.repository.TournamentRepository;

@Service
public class MatchServiceImpl implements MatchService {

	
	 @Autowired
	    private MatchRepository matchRepository;

	    @Autowired
	    private GroundRepository groundRepository;

	    @Autowired
	    private TournamentRepository tournamentRepository;

	    @Autowired
	    private TeamRepository teamRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Override
		public List<MatchDTO> getAllMatches() {
	        return matchRepository.findAll().stream()
	                .map(match -> modelMapper.map(match, MatchDTO.class))
	                .collect(Collectors.toList());
	    }

	    @Override
		public MatchDTO getMatchById(Long id) {
	        Optional<Match> match = matchRepository.findById(id);
	        Match m1=match.orElseThrow();
	        System.out.println(m1.getTeamA().getId());
	        return match.map(m -> modelMapper.map(m, MatchDTO.class)).orElse(null);
	    }

	    @Override
		public MatchDTO createMatch(MatchDTO matchDTO) {
	        Match match = modelMapper.map(matchDTO, Match.class);
	        // Set additional data if needed
//	        match.setGround(groundRepository.findById(matchDTO.getGroundId()).orElse(null));
//	        match.setTournament(tournamentRepository.findById(matchDTO.getTournamentId()).orElse(null));
//	        match.setTeamA(teamRepository.findById(matchDTO.getTeamAId()).orElse(null));
//	        match.setTeamB(teamRepository.findById(matchDTO.getTeamBId()).orElse(null));
	        Match savedMatch = matchRepository.save(match);
	        return modelMapper.map(savedMatch, MatchDTO.class);
	    }

	    @Override
		public MatchDTO updateMatch(Long id, MatchDTO matchDTO) {
	     Match match=matchRepository.findById(id).orElseThrow();
	       
	        match.setId(id);
	        match.setDate(matchDTO.getDate());
	        match.setTeamBScore(matchDTO.getTeamBScore());
	        match.setTeamAScore(matchDTO.getTeamAScore());
	        // Set additional data if needed
	        match.setResult(matchDTO.getResult());
	        match.setType(matchDTO.getType());
//	        match.setGround(groundRepository.findById(matchDTO.getGroundId()).orElse(null));
//	        match.setTournament(tournamentRepository.findById(matchDTO.getTournamentId()).orElse(null));
	        Team team1= teamRepository.findById(matchDTO.getTeamAId()).orElse(null);
	        match.setTeamA(team1);
	      Team team2=  teamRepository.findById(matchDTO.getTeamBId()).orElse(null);
	        match.setTeamB(team2);
	        
	        Match updatedMatch = matchRepository.save(match);
	        return modelMapper.map(updatedMatch, MatchDTO.class);
	    }

	    @Override
		public boolean deleteMatch(Long id) {
	        if (!matchRepository.existsById(id)) {
	            return false;
	        }
	        matchRepository.deleteById(id);
	        return true;
	    }
	}
