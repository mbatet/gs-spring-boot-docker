package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {


    //https://github.com/spring-guides/gs-serving-web-content


    //Si no posem el "/" per defecte, l'anira a buscar a static/index.html
    @GetMapping({"/", "/home", "/index.html"})
    public String home() {

        return "index";
    }

    @GetMapping("/about")
    public String about(@RequestParam(name="name", required=false, defaultValue="you") String name, Model model) {


        var message = "System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "about";
    }


    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
