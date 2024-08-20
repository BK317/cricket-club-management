package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
