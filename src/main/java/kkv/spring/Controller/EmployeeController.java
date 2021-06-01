package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.PathVariable;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

import java.security.Principal;

@Controller
public class EmployeeController {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Autowired
    private AccountRepository accountRepository;

    /*@PreAuthorize("#login == authentication.name")*/
    @GetMapping("users/{login}/inf")
    public String showInf(@PathVariable("login") String login, Model model){
        model.addAttribute("user",accountRepository.findByLogin(login));
        return "/employee/userData";
    }

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @GetMapping("/users")
    public String getUsers(Principal principal, Model model){
        var login = principal.getName();
        /*model.addAttribute("users",accountRepository.findAll());*/
        model.addAttribute("login",login);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
        return "/employee/main";
    }
}

