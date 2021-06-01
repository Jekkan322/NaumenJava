package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class EmployeeController {


    @Autowired
    private AccountRepository accountRepository;

    /*@PreAuthorize("#login == authentication.name")*/
    @GetMapping("users/{login}/inf")
    public String showInf(@PathVariable("login") String login, Model model){
        model.addAttribute("user",accountRepository.findByLogin(login));
        return "/employee/userData";
    }


    @GetMapping("/users")
    public String getUsers(Principal principal, Model model){
        var login = principal.getName();
        /*model.addAttribute("users",accountRepository.findAll());*/
        model.addAttribute("login",login);

        return "/employee/main";
    }
}

