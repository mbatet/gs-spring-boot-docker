package hello;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Controller
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



	/*
	@RequestMapping("/")
	public String home() {



		//Un cosa que es pot fer només desde Java 11,
		// aixi ens assegurem que el host realment s'esta executant en un java superior a 8

		log.info("[m:home] =============> greeting: " + greeting);

		return greeting;
	}*/

	/*
	* Provar:
	* 	http://localhost:8080
	* 	http://localhost:8080/greeting
	* 	http://localhost:8080/greeting?name=marina
	*
	* */


	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

		var message = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

		model.addAttribute("name", name);
		model.addAttribute("message", message);

		return "greeting";
	}


}
