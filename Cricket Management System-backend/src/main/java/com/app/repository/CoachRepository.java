package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

}
