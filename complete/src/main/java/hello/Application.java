package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
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





}
