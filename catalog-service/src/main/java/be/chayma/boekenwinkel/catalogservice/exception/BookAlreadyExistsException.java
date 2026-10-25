package be.chayma.boekenwinkel.catalogservice.exception;

/**
 * Wordt gegooid wanneer je een boek wil toevoegen met een ISBN dat al bestaat.
 */
public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("Er bestaat al een boek met ISBN " + isbn);
    }
}