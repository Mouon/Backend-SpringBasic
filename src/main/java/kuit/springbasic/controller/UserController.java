package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.dao.UserDao;
import kuit.springbasic.domain.User;
import kuit.springbasic.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;


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
        userDao.insert(newUser);
        return "redirect:/user/list";
    }


    /**
     * TODO: showUserList
     */
    @RequestMapping("/list")
    String showUserList(HttpServletRequest request){
        log.info("UserController.showUserList");
        if(UserSessionUtils.isLoggedIn(request.getSession())){
            request.setAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    /**
     * TODO: showUserUpdateForm
     */
    @RequestMapping("/updateForm")
    String showUserUpdateForm(@RequestParam("userId") String userId, HttpServletRequest request)throws SQLException {
        User user = userDao.findByUserId(userId);//id와 일치하는 user정보 찾아서 user에 할당
        if (user != null) {
            request.setAttribute("user",user);//사용자 정보를 "user"라는 "attribute"이름으로 저장
            return "/user/updateForm";
        }
        return "redirect:/";
    }

    /**
     * TODO: updateUser
     */
    @RequestMapping("/update")
    String updateUser(@ModelAttribute User user){
        userDao.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/profile.html")
    String profileUser(@ModelAttribute User user){
        userDao.update(user);
        return "redirect:/user/profile";
    }
}
