package kkv.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecondController {
    @GetMapping("/exit")
    public String getExit(){
        return "springController/springSecondController/third";
    }
}
