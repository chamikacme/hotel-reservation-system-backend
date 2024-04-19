package com.webdev.hotelRes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.hotelRes.dto.JwtResponseDTO;
import com.webdev.hotelRes.dto.MessageResponseDTO;
import com.webdev.hotelRes.dto.UserLoginDTO;
import com.webdev.hotelRes.entity.User;
import com.webdev.hotelRes.repository.UserRepository;
import com.webdev.hotelRes.security.jwt.JwtUtils;

@CrossOrigin(origins = "*")
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
        try{
            if(user.getEmail() == null || user.getEmail().isEmpty()){
                return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: Email is required!"));
            }

            if(user.getPassword() == null || user.getPassword().isEmpty()){
                return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: Password is required!"));
            }

            if(userRepository.existsByEmail(user.getEmail())){
                return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: This email has an account!"));
            }

            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            if(user.getFirstName() != null && !user.getFirstName().isEmpty()){
                newUser.setFirstName(user.getFirstName());
            }

            if(user.getLastName() != null && !user.getLastName().isEmpty()){
                newUser.setLastName(user.getLastName());
            }

            if(user.getPhone() != null && !user.getPhone().isEmpty()){
                newUser.setPhone(user.getPhone());
            }

            if(user.getRole() != null){
                newUser.setRole(user.getRole());
            }

            userRepository.save(newUser);

            return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));

        } catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            User user = userRepository.findByEmail(userLoginDTO.getEmail()).get();
        
            return ResponseEntity.ok(new JwtResponseDTO(jwt, user.getId(), user.getEmail()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponseDTO("Bad credentials:"));
        } catch (Exception e){
                return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }

    }

}
