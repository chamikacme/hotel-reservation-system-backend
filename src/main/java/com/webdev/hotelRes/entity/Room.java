package com.webdev.hotelRes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer occupants;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private Double unitPrice;
}
