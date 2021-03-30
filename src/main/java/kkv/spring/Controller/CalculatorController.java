package kkv.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculate(
            @RequestParam(value = "a",required = false) int a,
            @RequestParam(value = "b",required = false) int b,
            @RequestParam(value = "action",required = false) String action,
            Model model
    ){
        int result = 0;

        if(action!=null) {
            switch (action) {
                case ("mult"):
                    result = a * b;
                    break;
                case ("add"):
                    result = a + b;
                    break;
                case ("sub"):
                    result = a - b;
                    break;
                case ("div"):
                    result = a / b;
                    break;
                default:
                    break;
            }
        }
        model.addAttribute("calculate",result);
        return "springController/calculatorController/calc.html";
    }
}
