package be.chayma.boekenwinkel.catalogservice.exception;

/**
 * Wordt gegooid wanneer een boek met het gevraagde ISBN niet bestaat.
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("Geen boek gevonden met ISBN " + isbn);
    }
}