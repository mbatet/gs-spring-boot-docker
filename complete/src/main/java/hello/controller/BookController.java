package hello.controller;


import hello.model.Book;
import hello.model.Genre;
import hello.repository.BookRepository;
import hello.service.BookService;
import hello.service.GenreService;
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

    @Autowired
    GenreService genreService;

    //http://localhost:8080/demoapp/books/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    //http://localhost:8080/demoapp/books/new
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook(Model model) {

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        
        Book book = new Book();
        model.addAttribute("book", book);
        return "book";
    }


    //http://localhost:8080/demoapp/books/11
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable @NotNull Long id, Model model) {

        log.info("[m:get] Busquem llibre " + id);
        Book book = bookService.get(id);
        log.info("[m:get] Hem trobat " + book);
        model.addAttribute("book", book);

        //TODO: Això ha d'anar a un  @ModelAttribute i passar-ho per defecte semrpe al request sense tenir q nrecupewrar-hoc ada cop
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);


        return "book";
    }

    @RequestMapping(value = "/genre/{name}", method = RequestMethod.GET)
    public String findByGenre(@PathVariable @NotNull String name, Model model) {
        List<Book> books =  bookService.findByGenreName(name);
        model.addAttribute("books", books);

        return "books";
    }

    /*
     * En aquest cas, s'injecta el nou book com un json al requet body del PUT
     * */
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.PUT})
    //public String put(@RequestBody @NotNull Book book, BindingResult result, Model model) {
    public String put(@ModelAttribute @NotNull Book book, BindingResult result, Model model) {
        log.error("[m:put] ===================> result: " + result);
        log.warn("[m:put] ===================> PERSISTIM NOU LLIBRE: " + book);


        Book bookPersisted = bookService.save(book);
        model.addAttribute("book", book);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        return "book";
    }


}
