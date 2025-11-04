package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.Reservation;
import com.restaurant_management.restaurant_management.db.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {this.reservationRepository = reservationRepository;}
    public Reservation addReservation(Reservation reservation) {return reservationRepository.save(reservation);}
    public List<Reservation> getAllReservations() {return reservationRepository.findAll();}
    public void deleteReservation(Reservation reservation) {reservationRepository.delete(reservation);}
    public void updateReservation(Reservation reservation, Reservation newReservation) {
        Reservation myReservation = reservationRepository.findById(reservation.getId()).get();
        myReservation = newReservation;
        reservationRepository.save(myReservation);
    }
}
