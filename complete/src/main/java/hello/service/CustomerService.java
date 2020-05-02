package hello.service;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//valors possibles de scoped epenent del que volguem: singleton | prototype | request | session | application | websocket
@Scope(value = "singleton")
@Component(value = "customerService")
public class CustomerService {

    //https://www.baeldung.com/jsf-spring-boot-controller-service-dao
    //https://www.baeldung.com/spring-bean-scopes


    @Autowired
    private CustomerRepository repository;

    public String insertData()
    {
        //Moure tot això a al controller & service de Customer
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));


        //Genre(@NotNull String name)
        //Book(@NotNull String name, @NotNull String isbn, Genre genre)

        return "Congratulations! You have inserted a few rows!";

    }


    public String retrieveData()
    {

        List<Customer> customers = new ArrayList<Customer>();
        Iterable<Customer> iterator = repository.findAll();  //no ha calgut definir el metode findAll
        iterator.forEach(customers::add);
        Optional<Customer> customer = repository.findById(Long.valueOf(1)); //no ha calgut definir el metode findById
        List<Customer> someCustomers = repository.findByLastName("Dessler"); //no ha calgut definir el metode findByLastName, només declarar-lo
        List<String> customersWithPartOfName = repository.findUsersWithPartOfName("Ki");  //aquest metode si te una query jpa

        String result =" repository.findAll(): " + customers.size()  + " customer rows";
        result += "<br/>Customers: " + customers;
        result += "<br/>repository.findById(1): " +  (customer.isPresent()? customer.get().toString():"");
        result += "<br/>repository.findByLastName('Dessler'): " + someCustomers.size();
        result += "<br/>repository.findUsersWithPartOfName('Ki')): " +customersWithPartOfName;


        return result;

    }

}
