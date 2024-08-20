package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Ground;
import com.app.entities.Team;

public interface GroundRepository  extends JpaRepository<Ground, Long>{



}
