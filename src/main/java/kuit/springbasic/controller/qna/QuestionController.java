package kuit.springbasic.controller.qna;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.dao.QuestionDao;
import kuit.springbasic.domain.Question;
import kuit.springbasic.domain.User;
import kuit.springbasic.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;


@Slf4j
@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String showQuestionForm(HttpServletRequest request, Model model) {
        log.info("QuestionController.showQuestionForm");
        HttpSession session = request.getSession();

        if (UserSessionUtils.isLoggedIn(session)) {
            model.addAttribute("user", session.getAttribute("user"));
            return "qna/form";
        }

        return "redirect:/user/login";
    }

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question newQuestion, HttpSession session) {
        log.info("QuestionController.createQuestion");
        User user = (User) session.getAttribute("user");
        newQuestion.setWriter(user.getUserId());
        questionDao.insert(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/updateForm")
    public String showUpdateQuestionForm(@RequestParam int questionId, @SessionAttribute("user") User user, Model model) {
        if (user == null) {
            return "redirect:/user/login";
        }
        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(user)) {
            return "redirect:/qna/show?questionId=" + questionId;
        }
        model.addAttribute("question", question);
        return "qna/updateForm";
    }

    @PostMapping("/update")
    public String updateQuestion(@ModelAttribute Question updatedQuestion, HttpSession session, RedirectAttributes redirectAttributes) {

        log.info("QuestionController.updateQuestion");
        User user = (User) session.getAttribute("user");//User의 객체로 user 속성을 뽑ㅑ
        Question question = questionDao.findByQuestionId(updatedQuestion.getQuestionId());

        if(!UserSessionUtils.isLoggedIn(session)){
            return "redirect:/user/login";
        }
        if (!question.isSameUser(user)) {
            return "redirect:/";
        }
        question.setTitle(updatedQuestion.getTitle());
        question.setContents(updatedQuestion.getContents());
        questionDao.update(question);
        return "redirect:/qna/show?questionId=" + updatedQuestion.getQuestionId();
    }


    @GetMapping("/show")
    //@RequestParam("questionId")이거로 파라매터 추출하는거 맞나욥..?
    public String showQnA(@RequestParam("questionId") int questionId, Model model) throws SQLException {
        log.info("QnAController.showQnA");
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute("question", question);

        return "qna/show";
    }



}
