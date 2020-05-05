package hello.service;

import hello.model.Book;
import hello.model.Customer;
import hello.model.Genre;
import hello.repository.BookRepository;
import hello.repository.CustomerRepository;
import hello.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//https://www.baeldung.com/spring-bean-scopes
//valors possibles de scoped epenent del que volguem: singleton | prototype | request | session | application | websocket
@Scope(value = "singleton")
@Component(value = "adminService")
public class AdminService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    public String insertData()
    {
        //Moure tot això a al controller & service de Customer
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));


        Genre scyfi = genreRepository.save(new Genre("Scy-fi"));
        Genre adventure = genreRepository.save(new Genre("Adventure"));
        Genre drama = genreRepository.save(new Genre("Drama"));
        Genre science = genreRepository.save(new Genre("Science"));

        bookRepository.save(new Book("Brave New World","1234561", scyfi));
        bookRepository.save(new Book("Foundation","1234562", scyfi));
        bookRepository.save(new Book("Starship Troopers","1234563", scyfi));
        bookRepository.save(new Book("Solaris","1234564", scyfi));
        bookRepository.save(new Book("Dune","1234565", scyfi));
        bookRepository.save(new Book("Do androids dream of electric sheep?","1234566", scyfi));
        bookRepository.save(new Book("Ringworld","1234567", scyfi));
        bookRepository.save(new Book("The left hand of darkness","1234568", scyfi));
        bookRepository.save(new Book("Rendezvous with Rama","1234569", scyfi));
        bookRepository.save(new Book("Enders's game","1234570", scyfi));
        bookRepository.save(new Book("Red mars","1234571", scyfi));
        bookRepository.save(new Book("The Martian Chronicles","1234572", scyfi));
        bookRepository.save(new Book("I, Robot","1234573", scyfi));


        bookRepository.save(new Book("Harry Potter and the philosopher stone","1234574", adventure));
        bookRepository.save(new Book("The Lord Of The Rings","1234575", adventure));


        //Genre(@NotNull String name)
        //Book(@NotNull String name, @NotNull String isbn, Genre genre)

        return "Congratulations! You have inserted a few rows!";

    }


    public String retrieveData()
    {

        List<Customer> customers = new ArrayList<Customer>();
        Iterable<Customer> iterator = customerRepository.findAll();  //no ha calgut definir el metode findAll
        iterator.forEach(customers::add);
        Optional<Customer> customer = customerRepository.findById(Long.valueOf(1)); //no ha calgut definir el metode findById
        List<Customer> someCustomers = customerRepository.findByLastName("Dessler"); //no ha calgut definir el metode findByLastName, només declarar-lo
        List<String> customersWithPartOfName = customerRepository.findUsersWithPartOfName("Ki");  //aquest metode si te una query jpa

        String result =" repository.findAll(): " + customers.size()  + " customer rows";
        result += "<br/>Customers: " + customers;
        result += "<br/>repository.findById(1): " +  (customer.isPresent()? customer.get().toString():"");
        result += "<br/>repository.findByLastName('Dessler'): " + someCustomers.size();
        result += "<br/>repository.findUsersWithPartOfName('Ki')): " +customersWithPartOfName;


        return result;

    }


    public void deleteData()
    {
        customerRepository.deleteAll();
        bookRepository.deleteAll();
        genreRepository.deleteAll();

    }

}
