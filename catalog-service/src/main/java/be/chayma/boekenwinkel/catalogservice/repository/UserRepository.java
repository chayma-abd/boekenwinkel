package be.chayma.boekenwinkel.catalogservice.repository;

import be.chayma.boekenwinkel.catalogservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // Spring maakt automatisch de SQL query: SELECT * FROM app_user WHERE email = ?
    Optional<User> findByEmail(String email);

    // Spring maakt automatisch de SQL query: SELECT COUNT(*) > 0 FROM app_user WHERE email = ?
    boolean existsByEmail(String email);

}