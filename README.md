# gs-spring-boot-docker

## Spring boot + Docker (JDK 13)

Demo gradle project with Spring boot + Docker + Java 13 + Alpine distr

## Basic application content

* A Dockerfile
* A Spring boot application, with...
	* lombok for the getters and setters
	* added the basic MVC pattern with the domain classes and a controller and a repository layer: Customer, CustomerController, CustomerRepository ...
	* added basic spring data jpa functionalities  (h2 database + model entities + repository + saving and retrieving entities + dynamic query derivated from repository method names): Customer, CustomerRepository
	* added a service layer (CustomerService, BookService...) that it's not really necessary, b/c they don't have any useful implementation but accessing to the repository and you can do that directly from the controller ... it's utility solely resides in illustrate the use of the scope ...ex: @Scope(value = "singleton")	
	* use logback for the logging in it's default spring implementation (added a resources/logback-spring.xml file and that's it)
	* Basic @RestController to manage the REST API to save and retrieve entities
	* Test for the rest controllers with junit tests using TestRestTemplate
	* Added basic thymeleaf template and template fragments (use thymeleaf templates for the shared parts like headers, footer...), tested with a @WebMvcTest test
	* Added some views and forms with thymeleaf ... and form-bean mapping in the controller ... and validation and binding results ...
	* Added data caching with @Cache annotation
	* Added basic spring security config: 
		* login form for the web app + testing for the web app (authenticated part / unauthenticated part)
		* http basic auth for the rest controllers + testing for the authenticated rest controllers
	* Add some scheduled job (@Schedule)


## Roadmap

* Development, preproduction and production  profiles

* Add an application context,...right now there is none
* Get properties from the property file
* Basic webapp functionalities: @InitBinder, @ModelAttribute, Interception (HandlerMapping) ...
* Use ~~RestTemplate~~ WebClient for accesing third party rest services
* Migrate TestRestTemplate to WebTestClient 
* Add more complex auth with Spring security with LDAP & CAS integration
* searching & sorting & pagination (backend & frontend)

## backlog / wish list

* actuator & monitoring
* use jackson annotations to modify the marshalling / unmarshalling of the entities
* use jackson json views
* more testing
* add css styling and js static files ... add a bootstrap thymeleaf template
* generate and retrieve actuator data
* spring data REST: makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories
* add additional spring data goodies: pagination, sorting, query by example, support for transparent auditing (created, last changed)...
* spring cloud & spring cloud bus (to make a distributed system)
<!--
* [file explorer to see the project structure and the file contents] 
-->
## More info

[See this file](Docker%20+%20Spring.pdf)

## How to run the application locally

It's a gradle projecte, so you only need to execute: Gradle > Tasks > application > bootRun:

* > cd gs-spring-boot-docker/complete
* >./gradlew bootRun
* Go to http://localhost:8081/

## How to make (and run) a new docker image:

* > cd gs-spring-boot-docker/complete
* > ./gradlew assemble
* > mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
* > sudo docker build --build-arg DEPENDENCY=build/dependency -t mbatet/gs-spring-boot-docker:versionXX.YY .
* > sudo docker run -p 8081:8080 -t mbatet/gs-spring-boot-docker:versionXX.YY
* Go to http://localhost:8081/


## Credits

matet@gmail.com (Marina Batet)

* https://github.com/spring-guides/gs-serving-web-content
* https://spring.io/guides/gs/accessing-data-jpa/
* https://spring.io/guides/gs/spring-boot-docker/


