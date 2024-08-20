package com.app.service;

import java.util.List;

import com.app.dto.MatchDTO;

public interface MatchService {

	List<MatchDTO> getAllMatches();

	MatchDTO getMatchById(Long id);

	MatchDTO createMatch(MatchDTO matchDTO);

	MatchDTO updateMatch(Long id, MatchDTO matchDTO);

	boolean deleteMatch(Long id);

}