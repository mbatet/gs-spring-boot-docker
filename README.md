# gs-spring-boot-docker

## Spring boot + Docker (Java 13)

Demo gradle project with spring boot + Docker + Java 13 + Alpine distr

## Basic application content

* A Dockerfile
* spring boot application
* lombok for getters and setters
* added the basic MVC pattern with a controller and a service layer: CustomerController, CustomerService ...
* added basic spring data jpa functionalities  (h2 database + model entities + repository + saving and retrieving entities + dynamic query derivated from repository method names): Customer, CustomerRepository
* logback

## Roadmap

* Some views and forms ... and form-bean mapping in the controller ... and validation and binding results ...
* Cache
* Scheduling some job
* Development, preproduction and production  profiles
* Spring security with LDAP & CAS integration
* @InitBinder, @ModelAttribute  ..

## backlog / wish list

* A controller to manage the REST API to save and retrieve entities (marshalling, unmarshalling, jackson annotations, jackson json views ...)
* testing
* retrieve actuator data
* thymeleaf integration
* spring data REST: makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories
* add additional spring data goodies: pagination, sorting, query by example, support for transparent auditing (created, last changed)...
* spring cloud bus (to make a distributed system)

## More info

[See this file](Docker%20+%20Spring.pdf)

