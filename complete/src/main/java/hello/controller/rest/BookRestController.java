package hello.controller.rest;


import hello.model.Book;
import hello.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/rest/books")
public class BookRestController {


    //@Autowired
    //private BookRepository bookRepository;

    //De cara a not enir que fer mocks del repository (o pq no ens sabem), millor services que repositoris...
    @Autowired
    private BookService bookService;

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);


    //http://localhost:8080/books/
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> index() {


        List<Book> books = bookService.findAll();

        return books;
    }


    //http://localhost:8080/books/11
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable @NotNull Long id) {
        log.info("[m:get] BUsquem llibre " + id);

        Book book = bookService.get(id);

        log.info("[m:get] Hem trobat " + book);

        return book;
    }

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> findByGenre(@PathVariable @NotNull String name) {
        return bookService.findByGenreName(name);

    }

    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    //public Book save(@RequestBody @NotNull Book book, BindingResult result) {
    public Book save(@RequestBody Book book, BindingResult result) {

        log.info("[m:put] ===================> PERSISTIM NOU LLIBRE: " + book);
        Book bookPersisted = bookService.save(book);
        return bookPersisted;
    }


}
