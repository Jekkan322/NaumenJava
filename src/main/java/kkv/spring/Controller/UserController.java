package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private AccountRepository accountRepository;

    @Autowired
    public UserController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @GetMapping("/{login}/inf")
    public String showInf(@PathVariable("login") String login, Model model){
        model.addAttribute("user",accountRepository.findByLogin(login));
        return "/users/inf";
    }

    @GetMapping("/{login}")
    public String getProfile(@PathVariable("login") String login, Model model){
        model.addAttribute("profile",accountRepository.findByLogin(login));
        return "/authorization/profile";
    }


    @PostMapping("/{login}/loan")
    public String createLoan(){
        return "";
    }

    @GetMapping("/{login}/loan")
    public String returnLoan(@PathVariable("login") String login, Model model){
        model.addAttribute("login", login);
        return "/authorization/loan";
    }

    @GetMapping
    public String getUsers( Model model){
        model.addAttribute("users", accountRepository.findAll());
        return "/authorization/employer";
    }





}
