package kkv.spring.Controller;

import kkv.spring.models.Account;
import kkv.spring.models.AccountKey;
import kkv.spring.Repository.AccountRepository;
import kkv.spring.models.Roles;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Autowired
    private PasswordEncoder passwordEncoder;

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private AccountRepository accountRepository;

    @Autowired
    public AuthorizationController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @GetMapping()
    public String index(@RequestParam(name="logout", required = false) Boolean logout,
                        Model model){
        if(Boolean.TRUE.equals(logout))
            model.addAttribute("logout",logout);
        return "authorization/index";
    }

    @GetMapping("/registration")
    public String registration(
            @ModelAttribute("account")Account account,
            @ModelAttribute("accountKey") AccountKey accountKey
<<<<<<< Updated upstream
    ){
=======
            ){
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        return "authorization/registration";
    }

    @GetMapping("/enter")
    public String enter(@ModelAttribute("accountKey") AccountKey accountKey){
        return "authorization/enter";
    }

    @GetMapping("/identification")
    public String enter(Principal principal, Model model){
        System.out.println("ENTER");
        var login = principal.getName();
        var acc = accountRepository.findByLogin(login);
        System.out.println(login);
        if(acc!=null) {
            if (acc.getRolesSet().contains(Roles.EMPLOYEE)) {
                return "redirect:/users";
            }
            else if(acc.getRolesSet().contains(Roles.ADMIN))
                return "redirect:/users/roles";
            model.addAttribute("profile",acc);
            System.out.println("USER");
            return "redirect:/users/"+acc.getLogin();
        }
        return "redirect:/authorization";
    }

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
<<<<<<< Updated upstream
        var newPassword = passwordEncoder.encode(accountKey.getPassword());
        var id = passwordEncoder.encode(account.getLogin().substring(0,5));
        System.out.println(id);
        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("123"));
        account.setPassword(newPassword);
=======
        account.setPassword(accountKey.getPassword());
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        account.getRolesSet().add(Roles.USER);
        accountRepository.save(account);
        return "redirect:/authorization";
    }

}

