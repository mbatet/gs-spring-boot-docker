package hello.service;

import hello.controller.BookController;
import hello.model.Book;
import hello.model.Customer;
import hello.model.Genre;
import hello.repository.BookRepository;
import hello.repository.CustomerRepository;
import hello.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//valors possibles de scoped epenent del que volguem: singleton | prototype | request | session | application | websocket
@Scope(value = "singleton")
@Component(value = "bookService")
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    private static final Logger log = LoggerFactory.getLogger(BookService.class);


    public List<Book> findAll() {
        Iterable<Book> iterator = bookRepository.findAll();
        List<Book> books = new ArrayList<Book>();
        iterator.forEach(books::add);
        return books;
    }



    public Book get(Long id) {
        Book book = bookRepository.findById(id).get();
        log.info("[m:get] Hem trobat " + book);
        return book;
    }


    public List<Book>  findByGenreName(String name) {
        List<Book> books =  bookRepository.findByGenreName(name);
        return books;

    }


    public Book save(Book book) {
        log.info("[m:put] ===================> PERSISTIM NOU LLIBRE: " + book);
        Book bookPersisted = bookRepository.save(book);
        return bookPersisted;
    }


}
