package kuit.springbasic.controller.qna;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.db.MemoryQuestionRepository;
import kuit.springbasic.domain.Question;
import kuit.springbasic.domain.User;
import kuit.springbasic.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {

    private final MemoryQuestionRepository memoryQuestionRepository;

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
        memoryQuestionRepository.insert(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/updateForm")
    public String showUpdateQuestionForm(@RequestParam int questionId, @SessionAttribute("user") User user, Model model) {
        if (user == null) {
            return "redirect:/user/login";
        }
        Question question = memoryQuestionRepository.findByQuestionId(questionId);
        if (!question.isSameUser(user)) {
            return "redirect:/qna/show?questionId=" + questionId;
        }
        model.addAttribute("question", question);
        return "qna/updateForm";
    }

    @PostMapping("/update")
    public String updateQuestion(@ModelAttribute Question updatedQuestion, HttpSession session) {
        log.info("QuestionController.updateQuestion");
        User user = (User) session.getAttribute("user");//User의 객체로 user 속성을 뽑ㅑ
        Question question = memoryQuestionRepository.findByQuestionId(updatedQuestion.getQuestionId());

        if(!UserSessionUtils.isLoggedIn(session)){
            return "redirect:/user/login";
        }
        if (!question.isSameUser(user)) {
            return "redirect:/";
        }
        question.setTitle(updatedQuestion.getTitle());
        question.setContents(updatedQuestion.getContents());
        memoryQuestionRepository.update(question);
        return "redirect:/qna/show?questionId=" + updatedQuestion.getQuestionId();
    }

    @GetMapping("/show")
    //@RequestParam("questionId")이거로 파라매터 추출하는거 맞나욥..?
    public String showQnA(@RequestParam("questionId") int questionId, Model model) {
        log.info("QnAController.showQnA");

        Question question = memoryQuestionRepository.findByQuestionId(questionId);
        model.addAttribute("question", question);

        return "qna/show";
    }



}
