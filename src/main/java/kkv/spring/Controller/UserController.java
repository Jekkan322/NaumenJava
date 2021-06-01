
package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.Repository.RequestRepository;
import kkv.spring.models.FileUploadUtil;
import kkv.spring.models.RequestImages;
import kkv.spring.models.Request;
import kkv.spring.models.RequestStatus;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.security.access.prepost.PreAuthorize;
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @PreAuthorize("#login == authentication.name")
    @GetMapping("/{login}")
    public String getProfile(@PathVariable("login") String login, Model model,
                             @RequestParam Optional<String> inf){
        if(inf!=null)
            model.addAttribute("inf",inf.orElseGet(() -> ""));
        model.addAttribute("profile",accountRepository.findByLogin(login));
        return "/customer/main";
    }

<<<<<<< Updated upstream
    @PreAuthorize("#login == authentication.name")
=======
>>>>>>> Stashed changes
    @PostMapping("/{login}/loan")
    public String createLoan(@RequestParam("image1") MultipartFile multipartFile1,
                             @RequestParam("image2") MultipartFile multipartFile2,
                             @ModelAttribute("request") Request request,
<<<<<<< Updated upstream
                             @PathVariable("login") String login, Model model) throws IOException {
=======
            @PathVariable("login") String login, Model model) throws IOException {
>>>>>>> Stashed changes
        if(multipartFile1==null||multipartFile2==null)
            return "redirect:/users/"+login+"/loan";

        String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());

        String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());

        request.setLocationFirstPassportSpread(fileName1);
        request.setLocationResidencePermitPassportSpread(fileName2);
        model.addAttribute("path", "/users/"+login);
        request.setUserAccount(accountRepository.findByLogin(login));

        request.setDepartureDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(System.currentTimeMillis())));
        request.setRequestStatus(RequestStatus.PENDING);
        requestRepository.save(request);
        String uploadDir = "src/main/webapp/uploads/"+request.getId();
        FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
        FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
        model.addAttribute("inf","Ваша заявка успешно отправлена");
        return "/main/back";
    }

    @PreAuthorize("#login == authentication.name")
    @GetMapping("/{login}/loan")
    public String returnLoan(@PathVariable("login") String login,
                             Model model,
                             @ModelAttribute("request") Request request){
        if(
                accountRepository.
                        findByLogin(login).
                        getRequests().
                        stream().
                        filter(x->x.getEmployeeAccount()==null).count()>0
        ){
            model.addAttribute("inf","Вы уже отправили заявку на рассмотрение");
            return "redirect:/users/"+login;
        }
        model.addAttribute("login", login);
        return "/customer/loan";
    }

    @PreAuthorize("#login == authentication.name")
    @GetMapping("/{login}/requests")
    public String returnRequests(@PathVariable("login") String login,
                                 Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee", false);
        model.addAttribute("requests",accountRepository.findByLogin(login).getRequests());
        return "/customer/requests";
    }

    @PreAuthorize("#login == authentication.name")
    @GetMapping("/{login}/requests/{id}")
    public String returnRequest(@ModelAttribute("personPhoto") RequestImages image,
                                @PathVariable("login") String login,
                                @PathVariable("id") Long id,
                                Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee",false);
        model.addAttribute("request",requestRepository.findById(id).get());
        return "/customer/request";
    }

    @GetMapping("/pending_requests")
    public String getPendingRequests(Model model){
        model.addAttribute("isEmployee", true);
        model.addAttribute("requests", requestRepository.findByRequestStatus(RequestStatus.PENDING));
        return "/customer/requests";
    }

    @PreAuthorize("#login == authentication.name")
    @GetMapping("/{login}/reviewed_requests")
    public String getReviewedRequests(@PathVariable("login") String login,
                                      Model model){
        model.addAttribute("isEmployee", true);
        model.addAttribute("requests", requestRepository.findByEmployeeAccount(accountRepository.findByLogin(login)));
        return "/customer/requests";
    }

<<<<<<< Updated upstream
}


/*

package kkv.spring.Controller;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.Repository.RequestRepository;
import kkv.spring.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private AccountRepository accountRepository;
    private RequestRepository requestRepository;

    @Autowired
    public UserController(AccountRepository accountRepository,
                          RequestRepository requestRepository){
        this.accountRepository=accountRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping()
    public String getProfile(@ModelAttribute("login") String login, Model model,
                             @RequestParam Optional<String> inf){
        if(inf!=null)
            model.addAttribute("inf",inf.orElseGet(() -> ""));
        model.addAttribute("profile",accountRepository.findByLogin(login));
        model.addAttribute("login",login);
        return "/customer/main";
    }

    @PostMapping("/loan")
    public String createLoan(@RequestParam("image1") MultipartFile multipartFile1,
                             @RequestParam("image2") MultipartFile multipartFile2,
                             @ModelAttribute("request") Request request,
                             @ModelAttribute("login") String login, Model model) throws IOException {
        if(multipartFile1==null||multipartFile2==null)
            return "redirect:/user/loan";

        String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());

        String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());

        request.setLocationFirstPassportSpread(fileName1);
        request.setLocationResidencePermitPassportSpread(fileName2);
        model.addAttribute("path", "/user");
        request.setUserAccount(accountRepository.findByLogin(login));

        request.setDepartureDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(System.currentTimeMillis())));
        request.setRequestStatus(RequestStatus.PENDING);
        requestRepository.save(request);
        String uploadDir = "src/main/webapp/uploads/"+request.getId();
        FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
        FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
        model.addAttribute("inf","Ваша заявка успешно отправлена");
        return "/main/back";
    }

    @GetMapping("/loan")
    public String returnLoan(@ModelAttribute("login") String login,
                             Model model,
                             @ModelAttribute("request") Request request){
        if(
                accountRepository.
                        findByLogin(login).
                        getRequests().
                        stream().
                        filter(x->x.getEmployeeAccount()==null).count()>0
        ){
            model.addAttribute("inf","Вы уже отправили заявку на рассмотрение");
            return "redirect:/user";
        }
        model.addAttribute("login", login);
        return "/customer/loan";
    }

    @GetMapping("requests")
    public String returnRequests(@ModelAttribute("login") String login,
                                 Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee", false);
        model.addAttribute("requests",accountRepository.findByLogin(login).getRequests());
        return "/customer/requests";
    }



    @GetMapping("/requests/{id}")
    public String returnRequest(@ModelAttribute("personPhoto") RequestImages image,
                                @ModelAttribute("login") String login,
                                @PathVariable("id") Long id,
                                Model model){
        model.addAttribute("login", login);
        model.addAttribute("isEmployee",false);
        model.addAttribute("request",requestRepository.findById(id).get());
        return "/customer/request";
    }





=======
>>>>>>> Stashed changes
}
*/


