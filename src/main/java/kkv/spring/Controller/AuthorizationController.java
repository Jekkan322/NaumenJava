package kkv.spring.Controller;

import kkv.spring.DAO.AuthorizationDAO;
import kkv.spring.models.Account;
import kkv.spring.models.AccountKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private AuthorizationDAO authorizationDAO;

    @Autowired
    public AuthorizationController(AuthorizationDAO authorizationDAO){
        this.authorizationDAO=authorizationDAO;
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

    /*вход в кабинет*/
    @PostMapping("/enter")
    public String enter(@ModelAttribute("accountKey")AccountKey accountKey, Model model){
        if(authorizationDAO.hasKey(accountKey)) {
            var acc=authorizationDAO.getAccount(accountKey);

            if (acc.isAdmin()) {
                model.addAttribute("users",authorizationDAO.getUsers());
                return "/authorization/employer";
            }
            model.addAttribute("profile",acc);
            return "/authorization/profile";
        }
        return "redirect:/authorization";
    }

    @GetMapping("/loan")
    public String loan(){
        return "/authorization/loan";
    }

    /*зарегистрироваться*/
    @PostMapping("/registration")
    public String create(Model model,@ModelAttribute("account") @Valid Account account, BindingResult bindingResult,
                         @ModelAttribute("accountKey") @Valid AccountKey accountKey, BindingResult bindingResultKey
                         ){
        if(bindingResult.hasErrors()||bindingResultKey.hasErrors())
            return "/authorization/registration";
        if(authorizationDAO.hasEmail(accountKey)) {
            model.addAttribute("uniquenessOfEmail","Такой адресс уже используется");
            return "/authorization/registration";
        }

        account.setAccountKey(accountKey);

        authorizationDAO.create(account.getAccountKey(),account);
        return "redirect:/authorization";
    }





}
