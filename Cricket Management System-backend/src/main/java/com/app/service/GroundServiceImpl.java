package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.GroundDTO;
import com.app.entities.Ground;
import com.app.repository.GroundRepository;


@Service
public class GroundServiceImpl implements GroundService   {
	
	
	  @Autowired
	    private GroundRepository groundRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Override
		public GroundDTO addGround(GroundDTO groundDTO) {
	        Ground ground = modelMapper.map(groundDTO, Ground.class);
	        Ground savedGround = groundRepository.save(ground);
	        return modelMapper.map(savedGround, GroundDTO.class);
	    }

	    @Override
		public GroundDTO getGroundById(Long id) {
	        Ground ground = groundRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ground not found"));
	        return modelMapper.map(ground, GroundDTO.class);
	    }

	    @Override
		public List<GroundDTO> getAllGrounds() {
	        return groundRepository.findAll().stream()
	            .map(ground -> modelMapper.map(ground, GroundDTO.class))
	            .collect(Collectors.toList());
	    }

	    @Override
		public GroundDTO updateGround(Long id, GroundDTO groundDTO) {
	        Ground ground = groundRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ground not found"));

	        ground.setName(groundDTO.getName());
	        ground.setLocation(groundDTO.getLocation());
	        ground.setCapacity(groundDTO.getCapacity());

	        Ground updatedGround = groundRepository.save(ground);
	        return modelMapper.map(updatedGround, GroundDTO.class);
	    }

}
