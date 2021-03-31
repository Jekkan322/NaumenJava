package kkv.spring.Controller;

import kkv.spring.DAO.AuthorizationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private AuthorizationDAO authorizationDAO;

    @Autowired
    public UserController(AuthorizationDAO authorizationDAO){
        this.authorizationDAO=authorizationDAO;
    }

    @GetMapping("/{login}")
    public String showInf(@PathVariable("login") String login, Model model){
        model.addAttribute("user",authorizationDAO.getAccount(login));
        return "/users/inf";
    }

    @PostMapping("/{login}/loan")
    public String createLoan(){
        return "";
    }

}
