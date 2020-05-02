package hello.controller;

import hello.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String home() {

        log.info("HELLO");

        //Un cosa que es pot fer només desde Java 11,
        // aixi ens assegurem que el host realment s'esta executant en un java superior a 8
        var greeting = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

        return greeting;


    }

}
