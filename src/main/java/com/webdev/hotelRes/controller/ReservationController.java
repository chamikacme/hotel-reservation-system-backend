package com.webdev.hotelRes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.hotelRes.entity.Reservation;
import com.webdev.hotelRes.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class ReservationController {
    
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservatios());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
        try {
            Reservation reservationCreated = reservationService.createReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {

        try {
            Reservation updatedReservation = reservationService.updateReservation(id, reservation);
            return ResponseEntity.status(HttpStatus.OK).body(updatedReservation);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/rooms/{roomId}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationByRoomId(roomId));
    }

    


}
