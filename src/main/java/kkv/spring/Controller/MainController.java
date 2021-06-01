package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class MainController {

    private AccountRepository accountRepository;

    @Autowired
    public MainController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }



    @GetMapping
    public String redirectToMainPage(){

        return "redirect:/authorization";
    }

    @GetMapping("/exceptionHandling")
    public String handling(){
        return "redirect:/logout";
    }

}

