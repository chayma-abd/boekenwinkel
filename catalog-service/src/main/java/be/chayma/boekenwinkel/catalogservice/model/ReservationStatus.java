package be.chayma.boekenwinkel.catalogservice.model;

public enum ReservationStatus {
    PENDING,    // Gereserveerd, wacht op bevestiging
    CONFIRMED,  // Bevestigd, klaar om af te halen
    PICKED_UP   // Opgehaald en betaald (stock wordt dan verlaagd)
}