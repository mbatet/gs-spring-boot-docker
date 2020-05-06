package hello.service;

import hello.model.Genre;
import hello.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//valors possibles de scoped epenent del que volguem: singleton | prototype | request | session | application | websocket
@Scope(value = "singleton")
@Component(value = "genreService")
public class GenreService {


    @Autowired
    private GenreRepository genreRepository;

    private static final Logger log = LoggerFactory.getLogger(GenreService.class);


    @Cacheable("books")
    public List<Genre> findAll() {
        log.info("[m:findAll][NOCACHE] Anem a buscar a la base de dades");
        Iterable<Genre> iterator = genreRepository.findAll();
        List<Genre> genres = new ArrayList<Genre>();
        iterator.forEach(genres::add);
        return genres;
    }

    @CacheEvict(value="books", allEntries=true)
    public void deleteCacheBooks() {
        log.info("[m:deleteCacheBooks] Cache deleted");
    }



    public Genre get(Long id) {
        Genre genre = genreRepository.findById(id).get();
        log.info("[m:get] Hem trobat " + genre);
        return genre;
    }



    public Genre save(Genre genre) {
        log.info("[m:put] ===================> PERSISTIM NOU GENERE: " + genre);
        Genre genrePersisted = genreRepository.save(genre);
        return genrePersisted;
    }


}
