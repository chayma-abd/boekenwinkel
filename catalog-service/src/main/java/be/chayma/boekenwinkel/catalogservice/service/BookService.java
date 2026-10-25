package be.chayma.boekenwinkel.catalogservice.service;

import be.chayma.boekenwinkel.catalogservice.dto.BookDto;
import be.chayma.boekenwinkel.catalogservice.exception.BookAlreadyExistsException;
import be.chayma.boekenwinkel.catalogservice.exception.BookNotFoundException;
import be.chayma.boekenwinkel.catalogservice.model.Book;
import be.chayma.boekenwinkel.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Bevat de logica rond boeken en zet entities om naar DTO's en omgekeerd.
 * Zit tussen de controller en de repository.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Geeft alle boeken uit de catalogus terug.
     */
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    /**
     * Zoekt één boek op ISBN.
     *
     * @throws BookNotFoundException als er geen boek met dit ISBN bestaat
     */
    public BookDto getBook(String isbn) {
        return bookRepository.findById(isbn)
                .map(this::toDto)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    /**
     * Voegt een nieuw boek toe aan de catalogus.
     *
     * @throws BookAlreadyExistsException als het ISBN al bestaat
     */
    public BookDto createBook(BookDto dto) {
        if (bookRepository.existsById(dto.isbn())) {
            throw new BookAlreadyExistsException(dto.isbn());
        }
        Book saved = bookRepository.save(toEntity(dto));
        return toDto(saved);
    }

    private BookDto toDto(Book book) {
        return new BookDto(
                book.getIsbn(),
                book.getTitle(),
                List.copyOf(book.getAuthors()),
                book.getDescription(),
                book.getPrice(),
                book.getStock(),
                book.getGenre());
    }

    private Book toEntity(BookDto dto) {
        return new Book(
                dto.isbn(),
                dto.title(),
                new ArrayList<>(dto.authors()),
                dto.description(),
                dto.price(),
                dto.stock(),
                dto.genre());
    }
}