package kkv.spring.Controller;

import kkv.spring.dao.AuthorizationDAO;
import kkv.spring.models.Account;
import kkv.spring.models.AccountKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String registration(@ModelAttribute("account")Account account){
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
            model.addAttribute("profile",acc);
            return acc.isAdmin() ? "" : "/authorization/profile";
        }
        return "redirect:/authorization";
    }

    @GetMapping("loan")
    public String loan(){
        return "/authorization/loan";
    }

    /*зарегистрироваться*/
    @PostMapping("/registration")
    public String create(@ModelAttribute("account") Account account){
        if(authorizationDAO.hasKey(account.getAccountKey()))
            return "redirect:/authorization/registration";
        authorizationDAO.create(account.getAccountKey(),account);
        return "redirect:/authorization";
    }



}
