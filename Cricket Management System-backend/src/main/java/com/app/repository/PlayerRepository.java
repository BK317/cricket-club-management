package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.AccountStatus;
import com.app.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Additional query methods can be defined here
	List<Player> findByAccountStatus(AccountStatus accountStatus);
}
