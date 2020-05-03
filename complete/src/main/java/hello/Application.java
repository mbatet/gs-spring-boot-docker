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

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String home() {



		//Un cosa que es pot fer només desde Java 11,
		// aixi ens assegurem que el host realment s'esta executant en un java superior a 8
		var greeting = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

		log.info("[m:home] =============> greeting: " + greeting);

		return greeting;


	}



}
