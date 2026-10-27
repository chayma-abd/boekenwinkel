package be.chayma.boekenwinkel.catalogservice.controller;

import be.chayma.boekenwinkel.catalogservice.dto.ReservationRequest;
import be.chayma.boekenwinkel.catalogservice.model.Reservation;
import be.chayma.boekenwinkel.catalogservice.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * REST-controller voor het beheren van reserveringen.
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    // Constructor injectie
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Maakt een nieuwe reservering aan.
     * Geeft een 201 Created terug bij succes.
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationRequest request) {
        Reservation createdReservation = reservationService.createReservation(
                request.userId(),
                request.isbn(),
                request.quantity()
        );

        return ResponseEntity
                .created(URI.create("/api/reservations/" + createdReservation.getId()))
                .body(createdReservation);
    }
}