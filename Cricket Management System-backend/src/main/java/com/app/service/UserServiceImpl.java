package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.CreateUserDTO;
import com.app.dto.UserDTO;
import com.app.entities.AccountStatus;
import com.app.entities.Coach;
import com.app.entities.Player;
import com.app.entities.Role;
import com.app.entities.User;
import com.app.repository.CoachRepository;
import com.app.repository.PlayerRepository;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Autowired
    private CoachRepository cResp;
    
    @Autowired
    private PlayerRepository pResp;

    @Override
	public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
	public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
	public String createUser(CreateUserDTO createUserDTO) {
    	
    	 if (userRepository.existsByEmail(createUserDTO.getEmail())) {
             return "Error: Email is already in use!";
         }

         if (userRepository.existsByUsername(createUserDTO.getUsername())) {
             return   "Error: Username is already taken!";
         }
        User user=new User();
        
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setRole(createUserDTO.getRole());
        
        
        String pass=user.getPassword();
        
        user.setPassword(passwordEncoder.encode(pass));
        
        
        User savedUser = userRepository.save(user);
        
        if(createUserDTO.getRole()==Role.ROLE_PLAYER) {
        	
        	Player p=new Player();
        	p.setBattingStyle(createUserDTO.getPlayerBattingStyle());
        	p.setBowlingStyle(createUserDTO.getPlayerBowlingStyle());
        	p.setPosition(createUserDTO.getPlayerPosition());
        	p.setUser(savedUser);
        	p.setAccountStatus(AccountStatus.UNAPPROVED);
        	
            pResp.save(p);
        	
        }
        if(createUserDTO.getRole()==Role.ROLE_COACH) {
        	Coach c = new Coach();
        	c.setExperienceYears(Integer.parseInt(createUserDTO.getCoachExperienceYears()));
        	c.setSpecialization(createUserDTO.getCoachSpecialization());
        	c.setUser(savedUser);
        	c.setAccountStatus(AccountStatus.UNAPPROVED);
        	
        	cResp.save(c);
        	
        }
        
        
        
        
        
        
        
        return "User registered Successfully";
    }

    @Override
	public UserDTO updateUser(Long id, CreateUserDTO userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        modelMapper.map(userDetails, user);

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
	public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    
    public UserDTO getUserByEmail(String email) {
    	
    	
    Optional<User> user =userRepository.findByEmail(email);
    	
    return	modelMapper.map(user.get(),UserDTO.class);
    	
    }
}
