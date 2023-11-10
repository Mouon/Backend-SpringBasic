package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.dao.UserDao;
import kuit.springbasic.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final UserDao userDao;

    /**TODO: showLoginForm**/
    @GetMapping("/loginForm")
    //@RequestMapping(value = "/user" , method =RequestMethod.GET)
    public String showLoginForm() {
        log.info("LoginController.showLoginForm");
        return "/user/login";
    }

    /**
     * TODO: showLoginFailed
     */
    @PostMapping("/loginFailed")
    public String showLoginFailedPage(){
        log.info("LoginController.showLoginFailed");
        return "/user/loginFailed";
    }

    /**
     * TODO: login
     * login : @ModelAttribute
     */
    @PostMapping("/login")
    public String login(@ModelAttribute User loggedInUser, HttpServletRequest request)throws SQLException {
        log.info("LoginController.logIn");

        User user = userDao.findByUserId(loggedInUser.getUserId());

        if (user != null && user.isSameUser(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }
    /**
     * TODO: logout
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("LoginController.logOut");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }

}
