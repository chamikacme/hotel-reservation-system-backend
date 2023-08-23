package com.webdev.hotelRes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT reservation From Reservation reservation WHERE reservation.room.id = :roomId")
    List<Reservation> findReservationByRoomId(@Param("roomId") Long roomId);

}
