package tomytbo.example.springBootSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";//using thymeleaf to return home.html page
    }

    @GetMapping(value = {"/hello"})
    public String hello() {
        return "hello";//using thymeleaf to return home.html page
    }
}
