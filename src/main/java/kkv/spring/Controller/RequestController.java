package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.Repository.RequestRepository;
import kkv.spring.models.Account;
import kkv.spring.models.RequestStatus;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.security.Principal;

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
        req.setEmployeeAccount(accountRepository.findByLogin(principal.getName()));
        requestRepository.save(req);
        model.addAttribute("inf","Вы приняли заявку отправителя");
        return "customer/requestStatus";
    }

    @PostMapping("/{id}/reject")
    public String rejectRequest(Principal principal,
                                @PathVariable("id") Long id,
                                Model model){
        var req = requestRepository.findById(id).get();
        req.setRequestStatus(RequestStatus.REJECTED);
        req.setEmployeeAccount(accountRepository.findByLogin(principal.getName()));
        requestRepository.save(req);
        model.addAttribute("inf","Вы отклонили заявку отправителя");
        return "customer/requestStatus";
    }

    @PostMapping("/{id}/cancel")
    public String cancelRequest(@PathVariable("id") Long id,
                                Model model){
        var log = requestRepository.findById(id).get().getUserAccount().getLogin();
        model.addAttribute("login",log);
        System.out.println(log);
        deleteDirectory(new File("src/main/webapp/uploads/"+id));
        model.addAttribute("inf","Ваша заявка успешно отменена");
        requestRepository.deleteById(id);
        return "/customer/requestStatus";
    }

    @GetMapping("/{id}")
    public String showRequestForEmployee(@PathVariable("id") Long id,
                                Model model){
        var log = requestRepository.findById(id).get().getUserAccount();
        model.addAttribute("login",log);
        model.addAttribute("isEmployee",true);
        model.addAttribute("request",requestRepository.findById(id).get());
        return "/customer/request";
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
