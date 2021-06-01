package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.Repository.RequestRepository;

import kkv.spring.models.RequestImages;
import kkv.spring.models.RequestStatus;

import kkv.spring.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/requests")
public class RequestController {

    private AccountRepository accountRepository;

    private RequestRepository requestRepository;

    @Autowired
    public RequestController(RequestRepository requestRepository,
                             AccountRepository accountRepository){
        this.requestRepository=requestRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/{id}/approve")
    public String approveRequest(Principal principal,
                                 @PathVariable("id") Long id,
                                 Model model){
        var req = requestRepository.findById(id).get();
        req.setRequestStatus(RequestStatus.APPROVED);
        var accE = accountRepository.findByLogin(principal.getName());
        req.setEmployeeAccount(accE);
        var accU = req.getUserAccount();
        var newSet = accU.getRolesSet().

                stream().
                filter(x->x!= Roles.USER).
                collect(Collectors.toList());

        newSet.add(Roles.VERIFIED_USER);
        accU.setRolesSet(newSet);
        accountRepository.save(accU);
        requestRepository.save(req);
        model.addAttribute("inf","Вы подтвердили личность отправителя");
        model.addAttribute("path","/users");
        return "/main/back";
    }

    @PostMapping("/{id}/reject")
    public String rejectRequest(Principal principal,
                                @PathVariable("id") Long id,
                                Model model){
        var req = requestRepository.findById(id).get();
        req.setRequestStatus(RequestStatus.REJECTED);
        var acc = accountRepository.findByLogin(principal.getName());
        req.setEmployeeAccount(acc);
        requestRepository.save(req);
        model.addAttribute("inf","Вы отклонили личность отправителя");
        model.addAttribute("path","/users");
        return "/main/back";
    }

    @PostMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") Long id,
                                Model model){
        var log = requestRepository.findById(id).get().getUserAccount().getLogin();
        model.addAttribute("path","/users/"+log);
        System.out.println(log);
        deleteDirectory(new File("src/main/webapp/uploads/"+id));
        model.addAttribute("inf","Ваша заявка успешно отменена");
        requestRepository.deleteById(id);
        return "/main/back";
    }

    @GetMapping("/{id}")
    public String showRequestForEmployee(@ModelAttribute("personPhoto") RequestImages image,
                                         @PathVariable("id") Long id,
                                         Model model){
        var log = requestRepository.findById(id).get().getUserAccount();
        model.addAttribute("login",log);
        model.addAttribute("isEmployee",true);
        model.addAttribute("request",requestRepository.findById(id).get());
        return "customer/request";
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

}

