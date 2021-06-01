package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.models.Account;
import kkv.spring.models.Roles;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< Updated upstream
=======
import javax.management.relation.Role;
>>>>>>> Stashed changes
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private AccountRepository accountRepository;

    @Autowired
<<<<<<< Updated upstream
    private PasswordEncoder passwordEncoder;

    @Autowired
=======
>>>>>>> Stashed changes
    public AdminController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @PostMapping("/users/save_roles")
    public String saveRoles(@RequestParam("present") List<String> values, Model model){
        values.forEach(System.out::println);
        var people = accountRepository.findAll();
        for(var acc :people){
            if(values.contains(acc.getLogin()) && !acc.getRolesSet().contains(Roles.EMPLOYEE)) {
                accountRepository.save(changeRoles(Roles.USER,Roles.EMPLOYEE,acc));
            }else if(!values.contains(acc.getLogin()) && acc.getRolesSet().contains(Roles.EMPLOYEE)){
                accountRepository.save(changeRoles(Roles.EMPLOYEE,Roles.USER,acc));
                accountRepository.save(acc);
            }
        }
        model.addAttribute("inf","OK");
        model.addAttribute("path","/users/roles");
        return "/main/back";
    }

<<<<<<< Updated upstream
    /*@Secured("ADMIN")*/
=======
>>>>>>> Stashed changes
    @GetMapping("/users/roles")
    public String changeRoles(Model model){
        model.addAttribute("people",accountRepository.findAll().stream().filter(x->!x.getRolesSet().contains(Roles.ADMIN)).toArray());
        return "/admin/main";
    }

    private Account changeRoles(Roles currentRole, Roles newRole, Account acc){
        var newSet = acc.getRolesSet().stream().filter(x->x!=currentRole).collect(Collectors.toList());
        newSet.add(newRole);
        acc.setRolesSet(newSet);
        return acc;
    }

}

