package hello.controller;


import hello.model.Book;
import hello.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/books")
public class BookController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    private BookRepository bookRepository;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);


    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

     public Iterable<Book> index() {

        return bookRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Book get(@NotNull Long id) {
        return bookRepository.findById(id).get();
    }

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Book get(@NotNull String name) {
        return bookRepository.findByGenreName(name).get();
    }

    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Book put(@NotNull Book book, BindingResult result) {
        return bookRepository.save(book);
    }


}
