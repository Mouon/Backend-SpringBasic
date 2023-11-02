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
@Controller// 컨트롤러임을 알 수 있게하는 어노테이션
@RequiredArgsConstructor // 생성자 주입이 까다로운데 해당 어노테이션을 통해 생성자를 따로 만들필요가 없어져 주입 문제도 해결된다.
public class QuestionController {

    /* TODO: showQuestionForm */
    private final MemoryQuestionRepository memoryQuestionRepository;


    @GetMapping("/form")
    public String showQuestionForm(HttpServletRequest request) {
        log.info("QuestionController.showQuestionForm");
        HttpSession session = request.getSession();

        if (UserSessionUtils.isLoggedIn(session)) {
            return "/qna/form";
        }

        return "redirect:/user/login";
    }

    /**
     * TODO: createQuestion
     * createQuestion : @ModelAttribute
     */
    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question newQuestion){
        log.info("QuestionController.createQuestion");
        memoryQuestionRepository.insert(newQuestion);
        return "redirect:/";
    }



    /**
     * TODO: showUpdateQuestionForm
     * showUpdateQuestionFormV1 : @RequestParam, HttpServletRequest, Model
     * showUpdateQuestionFormV2 : @RequestParam, @SessionAttribute, Model
     */
    @GetMapping("/updateForm")
    public String showUpdateQuestionForm(@RequestParam int questionId, @SessionAttribute("user") User user
            , Model model) {
        //로그인 안됬으면 로그인 페이지로
        if (user == null) {
            return "redirect:/user/login";
        }
        Question question = memoryQuestionRepository.findByQuestionId(questionId);
        if (!question.isSameUser(user)) {
            return "/qna/show?questionId=" + questionId;
        }
        model.addAttribute("question", question);
        return "/qna/updateForm";
    }
    /**
     * TODO: updateQuestion
     */

}
