# gs-spring-boot-docker

## Spring boot + Docker (Java 13)

Demo gradle project with Spring boot + Docker + Java 13 + Alpine distr

## Basic application content

* A Dockerfile
* A Spring boot application, with...
	* lombok for the getters and setters
	* added the basic MVC pattern with a controller and a service layer: CustomerController, CustomerService ...
	* added basic spring data jpa functionalities  (h2 database + model entities + repository + saving and retrieving entities + dynamic query derivated from repository method names): Customer, CustomerRepository
	* logback
	* Test for the rest controllers with junit tests using TestRestTemplate
	* Added basic thymeleaf template, tested teh controller with a @WebMvcTest test

## Roadmap

* Add some views and forms with thymeleaf ... and form-bean mapping in the controller ... and validation and binding results ...
* Add basic spring security config
* Caching data (@Cache)
* Add some scheduled job (@Schedule)
* Development, preproduction and production  profiles
* Basic webapp functionalities: @InitBinder, @ModelAttribute, Interception (HandlerMapping) ...
* Use ~~RestTemplate~~ WebClient for accesing rest services
* Migrate TestRestTemplate to WebTestClient 
* Add more complex auth with Spring security with LDAP & CAS integration

## backlog / wish list

* A controller to manage the REST API to save and retrieve entities (marshalling, unmarshalling, jackson annotations, jackson json views ...)
* testing
* thymeleaf templates for the shared parts (headers, menu...) and add css styling and js static files
* retrieve actuator data
* spring data REST: makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories
* add additional spring data goodies: pagination, sorting, query by example, support for transparent auditing (created, last changed)...
* spring cloud bus (to make a distributed system)

## More info

[See this file](Docker%20+%20Spring.pdf)

