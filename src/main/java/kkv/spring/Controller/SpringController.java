package kkv.spring.Controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SpringController {

    @GetMapping("/hello")
    public String helloPage(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "surname",required = false) String surname,
            Model model
    ) {
        model.addAttribute("message","Hello, "+name+" "+surname);
        return "springController/first";
    }

    @GetMapping("/byebye")
    public String goodByePage(){
        return "springController/second";
    }

}
