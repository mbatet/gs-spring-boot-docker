package hello.controller;


import hello.Application;
import hello.model.Book;
import hello.model.Customer;
import hello.repository.BookRepository;
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
import java.util.Optional;


@RestController
@RequestMapping(value = "/books")
public class BookController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/



    @Autowired
    private BookRepository bookRepository;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);


    //http://localhost:8080/books/
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> index() {

        Iterable<Book> iterator = bookRepository.findAll();

        List<Book> books = new ArrayList<Book>();
        iterator.forEach(books::add);

        return books;
    }


    //http://localhost:8080/books/11
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Book get(@PathVariable @NotNull Long id) {
        log.info("[m:get] BUsquem llibre " + id);

        Book book = bookRepository.findById(id).get();

        log.info("[m:get] Hem trobat " + book);

        return book;
    }

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<Book> findByGenre(@PathVariable @NotNull String name) {
        return bookRepository.findByGenreName(name);

    }

    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Book put(@NotNull Book book, BindingResult result) {

        log.info("[m:put] ===================> PERSISTIM NOU LLIBRE: " + book);
        Book bookPersisted = bookRepository.save(book);

        return bookPersisted;
    }


}
