package com.webdev.hotelRes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.hotelRes.dto.JwtResponseDTO;
import com.webdev.hotelRes.dto.MessageResponseDTO;
import com.webdev.hotelRes.dto.UserLoginDTO;
import com.webdev.hotelRes.entity.User;
import com.webdev.hotelRes.repository.UserRepository;
import com.webdev.hotelRes.security.jwt.JwtUtils;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: Username is already taken!"));
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = userRepository.findByUsername(userLoginDTO.getUsername()).get();
        
        return ResponseEntity.ok(new JwtResponseDTO(jwt, user.getId(), user.getUsername()));
    }
}
