package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.PlayerDTOAdmin;
import com.app.entities.AccountStatus;
import com.app.entities.Player;
import com.app.entities.TrainingSession;
import com.app.entities.User;
import com.app.repository.PlayerRepository;
import com.app.repository.TrainingSessionRepository;
import com.app.repository.UserRepository;

@Service
public class PlayerServiceImpl implements PlayerService  {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private UserRepository uResp;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TrainingSessionRepository trainingSessionRepository; 
	
	 

	@Override
	public List<PlayerDTOAdmin> getAllPlayers() {
		return playerRepository.findAll().stream().map(player -> modelMapper.map(player, PlayerDTOAdmin.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<PlayerDTOAdmin> getPlayerById(Long id) {
		return playerRepository.findById(id).map(player -> modelMapper.map(player, PlayerDTOAdmin.class));
	}

	@Override
	public PlayerDTOAdmin addOrUpdatePlayer(PlayerDTOAdmin playerDTO) {
		Player player = modelMapper.map(playerDTO, Player.class);
		Player savedPlayer = playerRepository.save(player);
		return modelMapper.map(savedPlayer, PlayerDTOAdmin.class);
	}
	
	
	@Override
	public PlayerDTOAdmin updatePlayer(PlayerDTOAdmin playerDTO) {
		
		User user=uResp.findById(playerDTO.getUserId()).orElseThrow();
		
		Player player = modelMapper.map(playerDTO, Player.class);
		user.setUsername(player.getName());
		player.setUser(user);
		uResp.save(user);
	
		Player savedPlayer = playerRepository.save(player);
		return modelMapper.map(savedPlayer, PlayerDTOAdmin.class);
	}

	@Override
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	@Override
	
	public PlayerDTOAdmin approvePlayer(Long id) {
		Optional<Player> playerOpt = playerRepository.findById(id);
		if (playerOpt.isPresent()) {
			Player player = playerOpt.get();
			player.setAccountStatus(AccountStatus.APPROVED);
			Player savedPlayer = playerRepository.save(player);
			return modelMapper.map(savedPlayer, PlayerDTOAdmin.class);
		}
		return null; // Handle this case appropriately in your controller
	}

	@Override
	public PlayerDTOAdmin rejectPlayer(Long id) {
		Optional<Player> playerOpt = playerRepository.findById(id);
		if (playerOpt.isPresent()) {
			Player player = playerOpt.get();
			player.setAccountStatus(AccountStatus.REJECTED);
			Player savedPlayer = playerRepository.save(player);
			return modelMapper.map(savedPlayer, PlayerDTOAdmin.class);
		}
		return null; // Handle this case appropriately in your controller
	}
	
	 @Override
	    public List<TrainingSession> getTrainingSessionsByPlayerId(Long playerId) {
	        return trainingSessionRepository.findByPlayerId(playerId);
	    }

}
