package be.chayma.boekenwinkel.catalogservice.service;

import be.chayma.boekenwinkel.catalogservice.model.Book;
import be.chayma.boekenwinkel.catalogservice.model.Reservation;
import be.chayma.boekenwinkel.catalogservice.model.ReservationStatus;
import be.chayma.boekenwinkel.catalogservice.model.User;
import be.chayma.boekenwinkel.catalogservice.repository.BookRepository;
import be.chayma.boekenwinkel.catalogservice.repository.ReservationRepository;
import be.chayma.boekenwinkel.catalogservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // Constructor injection
    public ReservationService(ReservationRepository reservationRepository,
                              BookRepository bookRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    /**
     * Maakt een nieuwe reservering aan.
     */
    public Reservation createReservation(Long userId, String isbn, int quantity) {

        // 1. Zoek de user en het boek op
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden."));

        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Boek niet gevonden."));

        // 2. Check of er genoeg stock is (Edge case!)
        if (book.getStock() < quantity) {
            throw new RuntimeException("Niet genoeg stock beschikbaar.");
        }

        // 3. Maak de reservering aan
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setQuantity(quantity);
        reservation.setStatus(ReservationStatus.PENDING); // Start als PENDING
        reservation.setReservationDate(LocalDateTime.now());

        // 4. Sla op in de database
        // (De stock wordt pas verlaagd als de status op PICKED_UP wordt gezet!)
        return reservationRepository.save(reservation);
    }
}