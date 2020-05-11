package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@EnableCaching  //Aquesta anotacio nomes si volem habilitar la cache
//@EnableScheduling //Aquesta anotacio nomes si volem habilitar jobs
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("[m:main] =============> APPLICATION UP & RUNNIG: GOTO http://localhost:8080/demoapp");

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
