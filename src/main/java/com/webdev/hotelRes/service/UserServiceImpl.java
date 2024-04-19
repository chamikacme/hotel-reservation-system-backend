package com.webdev.hotelRes.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.dto.UpdatePasswordDTO;
import com.webdev.hotelRes.dto.UserDetailsResponseDTO;
import com.webdev.hotelRes.entity.User;
import com.webdev.hotelRes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found: " + id));
    }

    @Override
    public UserDetailsResponseDTO updateUser(Long id, User user) {
        User existingUser = getUserById(id);

        if (user.getFirstName() != null){
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null){
            existingUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPhone() != null){
            existingUser.setPhone(user.getPhone());
        }

        userRepository.save(existingUser);

        UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();

        userDetailsResponseDTO.setId(existingUser.getId());
        userDetailsResponseDTO.setFirstName(existingUser.getFirstName());
        userDetailsResponseDTO.setLastName(existingUser.getLastName());
        userDetailsResponseDTO.setEmail(existingUser.getEmail());
        userDetailsResponseDTO.setPhone(existingUser.getPhone());

        return userDetailsResponseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetailsResponseDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        User existingUser = getUserById(updatePasswordDTO.getId());
        if (updatePasswordDTO.getPassword() != null){
            existingUser.setPassword(passwordEncoder.encode(updatePasswordDTO.getPassword()));
        } else {
            throw new NoSuchElementException("Password cannot be null");
        }
        userRepository.save(existingUser);

        UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
        userDetailsResponseDTO.setId(existingUser.getId());
        userDetailsResponseDTO.setFirstName(existingUser.getFirstName());
        userDetailsResponseDTO.setLastName(existingUser.getLastName());
        userDetailsResponseDTO.setEmail(existingUser.getEmail());
        userDetailsResponseDTO.setPhone(existingUser.getPhone());

        return userDetailsResponseDTO;
    }
    
}
