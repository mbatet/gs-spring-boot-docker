package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@RequestMapping("/")
	public String home() {

		//Un cosa que es pot fer només desde Java 11,
		// aixi ens assegurem que el host realment s'esta executant en un java superior a 8
		var greeting = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");
		return greeting;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
