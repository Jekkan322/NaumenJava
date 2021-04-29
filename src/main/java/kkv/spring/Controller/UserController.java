package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
/*import kkv.spring.models.Request;*/
import kkv.spring.Repository.RequestRepository;
import kkv.spring.models.FileUploadUtil;
import kkv.spring.models.Request;
import kkv.spring.models.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@Controller
@RequestMapping("/users")
public class UserController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    @Autowired
    public UserController(AccountRepository accountRepository,
                          RequestRepository requestRepository){
        this.accountRepository=accountRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping("/{login}/inf")
    public String showInf(@PathVariable("login") String login, Model model){
        model.addAttribute("user",accountRepository.findByLogin(login));
        return "/users/inf";
    }

    @GetMapping("/{login}")
    public String getProfile(@PathVariable("login") String login, Model model){
        model.addAttribute("profile",accountRepository.findByLogin(login));
        return "/customer/profile";
    }


    @PostMapping("/{login}/loan")
    public String createLoan(@RequestParam("image1") MultipartFile multipartFile1,
            @RequestParam("image2") MultipartFile multipartFile2,
                             @ModelAttribute("request") Request request,
            @PathVariable("login") String login, Model model) throws IOException {
        String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());

        String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());

        request.setLocationFirstPassportSpread(fileName1);
        request.setLocationResidencePermitPassportSpread(fileName2);
        model.addAttribute("login", login);
        request.setUserAccount(accountRepository.findByLogin(login));
        request.setRequestStatus(RequestStatus.PENDING);
        requestRepository.save(request);
        String uploadDir = "src/main/webapp/uploads/"+request.getId();
        FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
        FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
        model.addAttribute("inf","Ваша заявка успешно отправлена");
        return "/customer/requestStatus";
    }

    @GetMapping("/{login}/loan")
    public String returnLoan(@PathVariable("login") String login,
                             Model model,
                             @ModelAttribute("request") Request request){
        model.addAttribute("login", login);
        return "/customer/loan";
    }

    @GetMapping("/{login}/requests")
    public String returnRequests(@PathVariable("login") String login,
                             Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee", false);
        model.addAttribute("requests",accountRepository.findByLogin(login).getRequests());
        return "/customer/requests";
    }

    @GetMapping("/{login}/requests/{id}")
    public String returnRequest(@PathVariable("login") String login,
                                @PathVariable("id") Long id,
                                 Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee",false);
        model.addAttribute("request",requestRepository.findById(id).get());
        return "/customer/request";
    }

    @GetMapping
    public String getUsers(Principal principal, Model model){
        var login = principal.getName();
        model.addAttribute("users",accountRepository.findAll());
        model.addAttribute("login",login);
        model.addAttribute("users", accountRepository.findAll());
        return "/authorization/employer";
    }


    @GetMapping("/pending_requests")
    public String getPendingRequests(Model model){
        model.addAttribute("isEmployee", true);
        model.addAttribute("requests", requestRepository.findByRequestStatus(RequestStatus.PENDING));
        return "/customer/requests";
    }

    @GetMapping("/{login}/reviewed_requests")
    public String getReviewedRequests(@PathVariable("login") String login,
                                      Model model){
        model.addAttribute("isEmployee", true);
        model.addAttribute("requests", requestRepository.findByEmployeeAccount(accountRepository.findByLogin(login)));
        return "/customer/requests";
    }



}
