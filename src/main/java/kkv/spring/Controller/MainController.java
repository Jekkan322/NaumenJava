package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
<<<<<<< Updated upstream
=======
import kkv.spring.models.Account;
import kkv.spring.models.Roles;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @GetMapping
    public String redirectToMainPage(Model model){
=======
    /*{
        if(accountRepository.findAll().stream().filter(x->!x.getRolesSet().contains(Roles.ADMIN))!=null){
            var admin = new Account("admin@mail.ru", "admin", Arrays.asList(Roles.ADMIN));
            admin.setPatronymic("admin");
            admin.setName("admin");
            admin.setSurname("admin");
            this.accountRepository.save(admin);
            System.out.println("CREATE ADMIN");
        }
    }*/

    @GetMapping
    public String redirectToMainPage(){

>>>>>>> Stashed changes
        return "redirect:/authorization";
    }

    @GetMapping("/exceptionHandling")
    public String handling(){
        return "redirect:/logout";
    }

}

