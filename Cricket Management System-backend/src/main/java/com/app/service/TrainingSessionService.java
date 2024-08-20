package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import com.app.entities.TrainingSession;

public interface TrainingSessionService {

	TrainingSession createTrainingSession(Long coachId, List<Long> playerIds, String sessionName,
			LocalDateTime sessionDate);

}