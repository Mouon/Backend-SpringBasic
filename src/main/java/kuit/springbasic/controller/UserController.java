package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.db.MemoryUserRepository;
import kuit.springbasic.domain.User;
import kuit.springbasic.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final MemoryUserRepository memoryUserRepository;


    /**
     * TODO: showUserForm
     */
    @GetMapping("/form")
    String showUserForm(Model model){
        log.info("UserController.showUserForm");
        return "/user/form";
    }

    /**
     * TODO: createUser
     * createUser : @ModelAttribute
     */
    @PostMapping("/signup")
    String createUser(@ModelAttribute User newUser){
        log.info("UserController.signupUser");
        memoryUserRepository.insert(newUser);
        return "redirect:/user/list";
    }


    /**
     * TODO: showUserList
     */
    @RequestMapping("/list")
    String showUserList(HttpServletRequest request){
        log.info("UserController.showUserList");
        if(UserSessionUtils.isLoggedIn(request.getSession())){
            request.setAttribute("users", memoryUserRepository.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    /**
     * TODO: showUserUpdateForm
     */
    @RequestMapping("/updateForm")
    String showUserUpdateForm(@RequestParam("userId") String userId, HttpServletRequest request){
        User user = memoryUserRepository.findByUserId(userId);
        if (user != null) {
            request.setAttribute("user",user);
            return "/user/updateForm";
        }
        return "redirect:/";
    }

    /**
     * TODO: updateUser
     */
    @RequestMapping("/update")
    String updateUser(@ModelAttribute User user){
        memoryUserRepository.update(user);
        return "redirect:/user/list";
    }
}
