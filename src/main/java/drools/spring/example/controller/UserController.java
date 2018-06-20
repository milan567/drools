package drools.spring.example.controller;

import drools.spring.example.domain.DTO.LoginRequestDTO;
import drools.spring.example.domain.DTO.LoginResponseDTO;
import drools.spring.example.domain.DTO.RegisterUserDTO;
import drools.spring.example.domain.User;
import drools.spring.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import drools.spring.example.security.TokenUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        System.out.println("Username: " + loginDTO.getUsername());
        System.out.println("Password: " + loginDTO.getPassword());
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String userToken = tokenUtils.generateToken(details);
            User user = this.userService.getUserByUsername(loginDTO.getUsername());
            System.out.println(user.getUserAuthorities().iterator().next().getAuthority().getName());
            return new ResponseEntity<>(new LoginResponseDTO(userToken, user.getId(),loginDTO.getUsername(),user.getUserAuthorities().iterator().next().getAuthority().getName()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUser)
    {
        System.out.println("usaoooooooooo");
        LoginResponseDTO responseDTO = this.userService.registerUser(registerUser);
        if(responseDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

}
