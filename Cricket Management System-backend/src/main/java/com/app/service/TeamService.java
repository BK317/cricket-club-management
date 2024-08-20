package com.app.service;

import java.util.List;

import com.app.dto.ResponseTeamDTO;
import com.app.dto.TeamDTO;

public interface TeamService {

	List<ResponseTeamDTO> getAllTeams();
	ResponseTeamDTO getTeamById(Long id);
    ResponseTeamDTO createTeam(ResponseTeamDTO teamDTO);
    ResponseTeamDTO updateTeam(Long id, ResponseTeamDTO teamDTO);
    void deleteTeam(Long id);

}