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
    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {


        var message = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "greeting";
    }




}
