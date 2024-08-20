package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.TrainingSession;
import com.app.service.TrainingSessionService;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping("/{coachId}/training-sessions")
    public ResponseEntity<TrainingSession> addTrainingSession(
            @PathVariable Long coachId,
            @RequestParam String sessionName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime sessionDate,
            @RequestParam List<Long> playerIds) {

        TrainingSession trainingSession = trainingSessionService.createTrainingSession(coachId, playerIds, sessionName, sessionDate);
        return new ResponseEntity<>(trainingSession, HttpStatus.CREATED);
    }
    
 
}