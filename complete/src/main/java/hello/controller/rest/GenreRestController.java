package hello.controller.rest;


import hello.model.Genre;
import hello.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping(value = "/rest/genres")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    private static final Logger log = LoggerFactory.getLogger(GenreRestController.class);



    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public List<Genre> index() {

        List<Genre> genres =  genreService.findAll();

        log.info("[m:index] genres: " + genres);
        return genres;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Genre get(@PathVariable  @NotNull Long id) {
        return genreService.get(id);
    }


    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Genre put(@RequestBody  @NotNull Genre genre, BindingResult result) {

        return genreService.save(genre);
    }


}
