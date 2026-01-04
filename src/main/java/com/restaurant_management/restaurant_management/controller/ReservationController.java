package com.restaurant_management.restaurant_management.controller;

import com.restaurant_management.restaurant_management.db.Reservation;
import com.restaurant_management.restaurant_management.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "reservation-controller")
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // CREATE
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    // READ
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    // UPDATE
    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id,
                                                         @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    // DELETE
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE WAITER
    @PutMapping("/reservation/{id}/waiter/{waiterId}")
    public ResponseEntity<Reservation> updateReservationWaiter(@PathVariable Integer id,
                                                               @PathVariable Integer waiterId) {
        return ResponseEntity.ok(reservationService.updateReservationWaiter(id, waiterId));
    }

    // UPDATE TABLES
    @PutMapping("/reservation/{id}/tables")
    public ResponseEntity<Reservation> updateReservationTables(@PathVariable Integer id,
                                                               @RequestBody List<Integer> tableIds) {
        return ResponseEntity.ok(reservationService.updateReservationTables(id, tableIds));
    }
}
