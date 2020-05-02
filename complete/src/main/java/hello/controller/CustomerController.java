package hello.controller;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    private CustomerRepository repository;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    //http://localhost:8080/customer/insertData
    @RequestMapping("/insertData")
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



    //http://localhost:8080/customer/retrieveData
    @RequestMapping("/retrieveData")
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
