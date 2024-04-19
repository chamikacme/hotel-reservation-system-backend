package com.webdev.hotelRes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsResponseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

}
