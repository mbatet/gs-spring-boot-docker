package hello.controller;


import hello.model.Book;
import hello.model.Genre;
import hello.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    private GenreRepository genreRepository;

    private static final Logger log = LoggerFactory.getLogger(GenreController.class);


    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public Iterable<Genre> index() {

        return genreRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Genre get(@PathVariable  @NotNull Long id) {
        return genreRepository.findById(id).get();
    }


    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Genre put(@RequestBody  @NotNull Genre genre, BindingResult result) {

        return genreRepository.save(genre);
    }


}
