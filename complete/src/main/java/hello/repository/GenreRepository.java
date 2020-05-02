package hello.repository;

import hello.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByName(@javax.validation.constraints.NotNull String name);

}