// src/main/java/com/app/repository/MatchRepository.java

package com.app.repository;

import com.app.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
