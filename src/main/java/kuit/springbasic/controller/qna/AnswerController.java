package kuit.springbasic.controller.qna;


import java.sql.SQLException;

import kuit.springbasic.dao.AnswerDao;
import kuit.springbasic.dao.QuestionDao;
import kuit.springbasic.domain.Answer;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerDao answerDao;
    private final QuestionDao questionDao;

    /**
     * TODO: addAnswer - @PostMapping
     * addAnswerV3 : @ModelAttribute, @ResponseBody
     */
    @ResponseBody
    @PostMapping("/api/qna/addAnswer")
    public Answer addAnswer(@ModelAttribute Answer answer) throws SQLException {
        log.info("AnswerController.addAnswer");

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return savedAnswer;
    }

}