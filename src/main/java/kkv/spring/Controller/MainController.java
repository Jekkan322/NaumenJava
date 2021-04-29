package kkv.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String redirectToMainPage(){
        return "redirect:/authorization";
    }

}
