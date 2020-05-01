package hello;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private CustomerRepository repository;


	@RequestMapping("/")
	public String home() {

		log.info("HELLO");

		//Un cosa que es pot fer només desde Java 11,
		// aixi ens assegurem que el host realment s'esta executant en un java superior a 8
		var greeting = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

		return greeting;


	}


	//http://localhost:8080/insertData

	@RequestMapping("/insertData")
	public String insertData()
	{
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));


		List<Customer> customers = new ArrayList<Customer>();


		Iterable<Customer> iterator = repository.findAll();
		iterator.forEach(customers::add);

		return "Congratulations! You have inserted a few rows: " + customers.size();

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}
