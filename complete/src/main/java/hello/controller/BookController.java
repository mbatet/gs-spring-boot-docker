package hello.controller;


import hello.model.Book;
import hello.repository.BookRepository;
import hello.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/books")
public class BookController {


    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    //http://localhost:8080/books/
    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String index(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    //http://localhost:8080/books/11
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public String get(@PathVariable @NotNull Long id, Model model) {

        log.info("[m:get] Busquem llibre " + id);
        Book book = bookService.get(id);
        log.info("[m:get] Hem trobat " + book);
        model.addAttribute("book", book);

        return "book";
    }

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public String findByGenre(@PathVariable @NotNull String name, Model model) {
        List<Book> books =  bookService.findByGenreName(name);
        model.addAttribute("books", books);

        return "books";
    }

    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public String put(@RequestBody @NotNull Book book, BindingResult result, Model model) {

        log.info("[m:put] ===================> PERSISTIM NOU LLIBRE: " + book);
        Book bookPersisted = bookService.save(book);
        model.addAttribute("book", book);

        return "book";
    }


}
