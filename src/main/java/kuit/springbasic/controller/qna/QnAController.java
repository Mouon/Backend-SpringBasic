package kuit.springbasic.controller.qna;


import kuit.springbasic.dao.AnswerDao;
import kuit.springbasic.dao.QuestionDao;
import kuit.springbasic.domain.Answer;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QnAController {

    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    /**
     * TODO: showQnA
     */
    @RequestMapping("/show")
    public String showQnA(@RequestParam Long questionId, Model model) throws SQLException {
        log.info("QuestionController.showQnA");

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        return "/qna/show";
    }


}
