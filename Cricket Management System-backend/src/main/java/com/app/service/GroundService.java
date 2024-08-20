package com.app.service;

import java.util.List;

import com.app.dto.GroundDTO;

public interface GroundService {

	GroundDTO addGround(GroundDTO groundDTO);

	GroundDTO getGroundById(Long id);

	List<GroundDTO> getAllGrounds();

	GroundDTO updateGround(Long id, GroundDTO groundDTO);

}