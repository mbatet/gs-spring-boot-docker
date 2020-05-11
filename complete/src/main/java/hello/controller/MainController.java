package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {


    //https://github.com/spring-guides/gs-serving-web-content
    private static final Logger log = LoggerFactory.getLogger(MainController.class);


    //Si no posem el "/" per defecte, l'anira a buscar a static/index.html
    @GetMapping({"/", "/home", "/index.html"})
    public String home() {

        return "index";
    }

    @GetMapping("/about")
    public String about(@RequestParam(name="name", required=false, defaultValue="you") String name, Model model) {


        var message = "System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("[m:about] auth: " + auth);
        log.info("[m:about] auth.isAuthenticated(): " + auth.isAuthenticated());
        log.info("[m:about] auth.getName(): " + auth.getName());
        log.info("[m:about] auth.getPrincipal(): " + auth.getPrincipal());
        log.info("[m:about] auth.getClass(): " + auth.getClass().getName());
        log.info("[m:about] auth.getPrincipal().getClass: " + auth.getPrincipal().getClass().getName());



        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "about";
    }


    // Login form
    @RequestMapping("/login")
    public String login() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("[m:login] auth: " + auth);
        log.info("[m:login] auth.isAuthenticated(): " + auth.isAuthenticated());
        log.info("[m:login] auth.getName(): " + auth.getName());
        log.info("[m:login] auth.getPrincipal(): " + auth.getPrincipal());
        log.info("[m:login] auth.getClass(): " + auth.getClass().getName());
        log.info("[m:login] auth.getPrincipal().getClass: " + auth.getPrincipal().getClass().getName());


        //TODO: spring security mal configurat, si no estas autenticat no hauries de tenir oauth
        if(auth.isAuthenticated() && auth.getClass().getName().contains("UsernamePasswordAuthenticationToken"))
        {
            //Jo crec que això no bva pq la accio de login es sobreescriu...
            //return "index";
            //log.info("[m:login] auth: " + auth);
        }

        return "login";
    }

    //TODO: Ha deixat d'anar al posar el context path cal aclarir
    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {

        log.info("[m:login] =====================> AQUESTA ES LA PART QUE SEMBLA QUE NO FUNCIONA");

        model.addAttribute("loginError", true);
        return "login";
    }

}
