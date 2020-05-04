package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        var message = "Hello Docker World.  System.getProperty(\"java.version\") is....: " + System.getProperty("java.version");

        model.addAttribute("name", name);
        model.addAttribute("message", message);

        //https://github.com/spring-guides/gs-serving-web-content

        return "greeting";
    }
}
