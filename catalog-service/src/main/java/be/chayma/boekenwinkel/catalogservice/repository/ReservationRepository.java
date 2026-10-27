package be.chayma.boekenwinkel.catalogservice.repository;

import be.chayma.boekenwinkel.catalogservice.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    // Om alle reserveringen van een specifieke gebruiker op te halen
    List<Reservation> findByUserId(Long userId);

}