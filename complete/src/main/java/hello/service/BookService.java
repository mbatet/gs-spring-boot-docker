package hello.service;

import hello.model.Book;
import hello.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
