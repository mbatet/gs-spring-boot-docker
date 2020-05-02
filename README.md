# gs-spring-boot-docker

## Spring boot + Docker (Java 13)

Demo gradle project with spring boot + Docker + Java 13 + Alpine distr

## Basic application content

* A Dockerfile
* spring boot application
* spring data jpa (h2 database + model entities + repository + saving and retrieving entities)
* lombok for getters and setters

## Roadmap

* logback
* A /customer controller & service class to do the processing that now is in Application.java class
* A controller to manage the REST API to save and retrieve entities
* Cache
* Scheduling some job
* some views
* add basic spring data functionalities: dynamic query derivation from repository method names, named queries, support for transparent auditing (created, last changed), etc...
* Spring security with LDAP & CAS integration
* Development, preproduction and production  profiles

## backlog / wish list
* testing
* retrieve actuator data
* thymeleaf integration
* spring data REST: makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories
* spring cloud bus (to make a distributed system)
## More info

[See this file](Docker%20+%20Spring.pdf)

