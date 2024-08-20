package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseTeamDTO;
import com.app.dto.TeamDTO;
import com.app.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<ResponseTeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTeamDTO> getTeamById(@PathVariable Long id) {
        ResponseTeamDTO team = teamService.getTeamById(id);
        return team != null ? new ResponseEntity<>(team, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody  ResponseTeamDTO teamDTO) {
        ResponseTeamDTO createdTeam = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTeamDTO> updateTeam(@PathVariable Long id, @RequestBody ResponseTeamDTO teamDTO) {
        ResponseTeamDTO updatedTeam = teamService.updateTeam(id, teamDTO);
        return updatedTeam != null ? new ResponseEntity<>(updatedTeam, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
    
	
}
