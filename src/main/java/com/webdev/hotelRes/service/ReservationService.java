package com.webdev.hotelRes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Reservation;

@Service
public interface ReservationService {
    List<Reservation> getAllReservatios();
    Reservation getReservationById(Long id);
    List<Reservation> getReservationByRoomId(Long roomId);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}
