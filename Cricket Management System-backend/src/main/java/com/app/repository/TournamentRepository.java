package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Team;
import com.app.entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {


}
