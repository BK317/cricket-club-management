package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Coach;
import com.app.entities.Player;
import com.app.entities.TrainingSession;
import com.app.repository.CoachRepository;
import com.app.repository.PlayerRepository;
import com.app.repository.TrainingSessionRepository;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService   {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private PlayerRepository playerRepository;

    
    @Override
	public TrainingSession createTrainingSession(Long coachId, List<Long> playerIds, String sessionName, LocalDateTime sessionDate) {
        Coach coach = coachRepository.findById(coachId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<Player> players = playerRepository.findAllById(playerIds);

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setSessionName(sessionName);
        trainingSession.setSessionDate(sessionDate);
        trainingSession.setCoach(coach);
        trainingSession.setPlayers(players);

        return trainingSessionRepository.save(trainingSession);
    }
}
