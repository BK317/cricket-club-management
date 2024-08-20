package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.CreateUserDTO;
import com.app.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	Optional<UserDTO> getUserById(Long id);

	String createUser(CreateUserDTO createUserDTO);

	UserDTO updateUser(Long id, CreateUserDTO userDetails);

	void deleteUser(Long id);
	
	UserDTO getUserByEmail(String email);

}