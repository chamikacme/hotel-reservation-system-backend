package com.webdev.hotelRes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
