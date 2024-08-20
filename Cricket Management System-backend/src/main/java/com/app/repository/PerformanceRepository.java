package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

	List<Performance> findByPlayerId(Long playerId);

}
