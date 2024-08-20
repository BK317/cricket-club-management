package com.app.dto;

import com.app.entities.Coach;
import com.app.entities.Player;
import com.app.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String role;
    private String email;
    private String phoneNumber;

}
