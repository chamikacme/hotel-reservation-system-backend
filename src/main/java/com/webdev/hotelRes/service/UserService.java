package com.webdev.hotelRes.service;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.dto.UpdatePasswordDTO;
import com.webdev.hotelRes.dto.UserDetailsResponseDTO;
import com.webdev.hotelRes.entity.User;

@Service
public interface UserService {
    User getUserById(Long id);
    UserDetailsResponseDTO updateUser(Long id, User user);
    UserDetailsResponseDTO updatePassword(UpdatePasswordDTO updatePasswordDTO);
    void deleteUser(Long id);
}
