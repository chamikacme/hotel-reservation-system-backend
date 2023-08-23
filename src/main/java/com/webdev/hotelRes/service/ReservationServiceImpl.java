package com.webdev.hotelRes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Reservation;
import com.webdev.hotelRes.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository; 

	@Override
	public List<Reservation> getAllReservatios() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation getReservationById(Long id) {
		return reservationRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

	@Override
	public Reservation createReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation updateReservation(Long id, Reservation reservation) {
		Reservation exiistingRes = getReservationById(id);

        exiistingRes.setCheckIn(reservation.getCheckIn());
        exiistingRes.setCheckOut(reservation.getCheckOut());
        exiistingRes.setStatus(reservation.getStatus());
        exiistingRes.setAmount(reservation.getAmount());

        return reservationRepository.save(exiistingRes);
	}

	@Override
	public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
	}

    @Override
    public List<Reservation> getReservationByRoomId(Long roomId) {
        return reservationRepository.findReservationByRoomId(roomId);
    }

	
}
