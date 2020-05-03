package hello.repository;

import hello.model.Book;
import hello.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

public interface BookRepository extends CrudRepository<Book, Long> {


    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);

    /*En cas de afegir una nova consulta una mica mes complexa, es pot fer servir la annotació '@Query'*/
    @Query("FROM Book p WHERE p.genre.name = :name")
    List<Book> findByGenreName(String name);
}