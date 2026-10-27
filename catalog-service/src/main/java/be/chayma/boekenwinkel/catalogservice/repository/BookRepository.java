package be.chayma.boekenwinkel.catalogservice.repository;

import be.chayma.boekenwinkel.catalogservice.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    // Spring maakt automatisch de SQL query hiervoor!
    Optional<Book> findByIsbn(String isbn);

}