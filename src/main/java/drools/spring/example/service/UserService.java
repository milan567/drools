package drools.spring.example.service;


import drools.spring.example.domain.DTO.LoginResponseDTO;
import drools.spring.example.domain.DTO.RegisterUserDTO;
import drools.spring.example.domain.User;

import java.util.List;


public interface UserService {
    User save(User user);

    LoginResponseDTO registerUser(RegisterUserDTO registerUser);

    User getUserById(Integer userId);

    User getUserByUsername(String username);
}
