package kkv.spring.Controller;


import kkv.spring.models.Account;
import kkv.spring.models.AccountKey;
import kkv.spring.Repository.AccountRepository;
import kkv.spring.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {




    private AccountRepository accountRepository;

    @Autowired
    public AuthorizationController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @GetMapping()
    public String index(){
        return "authorization/index";
    }

    @GetMapping("/registration")
    public String registration(
            @ModelAttribute("account")Account account,
            @ModelAttribute("accountKey") AccountKey accountKey
            ){


        return "authorization/registration";
    }

    @GetMapping("/enter")
    public String enter(@ModelAttribute("accountKey") AccountKey accountKey){
        return "authorization/enter";
    }


    @GetMapping("/tryenter")
    public String enter(Principal principal, Model model){
        System.out.println("ENTER");
        var acc = accountRepository.findByLogin(principal.getName());
        System.out.println(principal.getName());
        if(acc!=null) {
            if (acc.getRolesSet().contains(Roles.EMPLOYEE)) {
                model.addAttribute("users",accountRepository.findAll());
                System.out.println("ADMIN");
                return "/authorization/employer";
            }
            model.addAttribute("profile",acc);
            System.out.println("USER");
            return "/authorization/profile";
        }
        return "redirect:/authorization";
    }

    @GetMapping("/loan")
    public String loan(){
        return "/authorization/loan";
    }

    @GetMapping("/createRoom")
    public String createRoom(){

        return "/authorization/room";
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "/authorization/hello";
    }

    /*зарегистрироваться*/
    @PostMapping("/registration")
    public String create(Model model,@ModelAttribute("account") @Valid Account account, BindingResult bindingResult,
                         @ModelAttribute("accountKey") @Valid AccountKey accountKey, BindingResult bindingResultKey
                         ){
        if(bindingResult.hasErrors()||bindingResultKey.hasErrors())
            return "/authorization/registration";
        if(accountRepository.existsById(accountKey.getLogin())) {
            model.addAttribute("uniquenessOfEmail","Такой адресс уже используется");
            return "/authorization/registration";
        }

        account.setLogin(accountKey.getLogin());
        account.setPassword(accountKey.getPassword());
        account.setRolesSet(Arrays.asList(Roles.USER));
        accountRepository.save(account);
        return "redirect:/authorization";
    }





}
