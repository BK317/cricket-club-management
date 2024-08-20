package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.TrainingSession;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    // Additional query methods can be added here if needed
	
	@Query("SELECT ts FROM TrainingSession ts JOIN ts.players p WHERE p.id = :playerId")
	List<TrainingSession> findByPlayerId(Long playerId);
}
