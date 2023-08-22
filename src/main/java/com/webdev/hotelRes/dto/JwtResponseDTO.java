package com.webdev.hotelRes.dto;
    
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private Long id;
    private String username;

}


