package mk.ukim.finki.courses.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// In the finished app we will not be using these controllers but rest controllers
// Purely used for testing purposes

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getHomePage() {
        return "index";
    }
}
