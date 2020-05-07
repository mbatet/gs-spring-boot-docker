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
	* Added basic spring security config


## Roadmap

* http basic auth for the rest controllers + testing the rest controllers when they are authenticated
* Get properties from the property file
* Add an application context,...right now there is none
* Add some scheduled job (@Schedule)
* Development, preproduction and production  profiles
* Basic webapp functionalities: @InitBinder, @ModelAttribute, Interception (HandlerMapping) ...
* Use ~~RestTemplate~~ WebClient for accesing rest services
* Migrate TestRestTemplate to WebTestClient 
* Add more complex auth with Spring security with LDAP & CAS integration
* Entities list pagination (backend & frontend)

## backlog / wish list

* use jackson annotations to modify the marshalling / unmarshalling of the entities
* use jackson json views
* more testing
* add css styling and js static files ... add a bootstrap thymeleaf template
* generate and retrieve actuator data
* spring data REST: makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories
* add additional spring data goodies: pagination, sorting, query by example, support for transparent auditing (created, last changed)...
* spring cloud & spring cloud bus (to make a distributed system)

## More info

[See this file](Docker%20+%20Spring.pdf)


## Credits

matet@gmail.com (Marina Batet)

* https://github.com/spring-guides/gs-serving-web-content
* https://spring.io/guides/gs/accessing-data-jpa/
* https://spring.io/guides/gs/spring-boot-docker/


