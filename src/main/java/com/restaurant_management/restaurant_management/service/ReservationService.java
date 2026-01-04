package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final WaiterRepository waiterRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              WaiterRepository waiterRepository,
                              RestaurantTableRepository restaurantTableRepository) {
        this.reservationRepository = reservationRepository;
        this.waiterRepository = waiterRepository;
        this.restaurantTableRepository = restaurantTableRepository;
    }

    // CREATE
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // READ
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
    }

    // UPDATE
    public Reservation updateReservation(Integer id, Reservation newReservation) {
        Reservation reservation = getReservationById(id);

        reservation.setCustomerName(newReservation.getCustomerName());
        reservation.setEmail(newReservation.getEmail());
        reservation.setDate(newReservation.getDate());
        reservation.setTime(newReservation.getTime());

        return reservationRepository.save(reservation);
    }

    // DELETE
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    // update waiter of a reservation
    public Reservation updateReservationWaiter(Integer reservationId, Integer waiterId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId +  " not found"));

        Waiter waiter = waiterRepository.findById(waiterId)
                .orElseThrow(() -> new RuntimeException("Waiter with id " + waiterId + " not found"));

        reservation.setWaiter(waiter);

        return reservationRepository.save(reservation);
    }

    // update TABLES of a reservation
    public Reservation updateReservationTables(Integer reservationId, List<Integer> tableIds) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with id " + reservationId +  " not found"));

        List<RestaurantTable> tables = restaurantTableRepository.findAllById(tableIds);
        if (tables.isEmpty()) {
            throw new RuntimeException("No tables found for ids: " + tableIds);
        }

        reservation.setRestaurantTables(tables);

        return reservationRepository.save(reservation);
    }
}
