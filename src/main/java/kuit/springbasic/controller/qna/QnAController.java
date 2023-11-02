package kuit.springbasic.controller.qna;


import ch.qos.logback.core.model.Model;
import kuit.springbasic.db.MemoryAnswerRepository;
import kuit.springbasic.db.MemoryQuestionRepository;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QnAController {

    private final MemoryAnswerRepository memoryAnswerRepository;
    private final MemoryQuestionRepository memoryQuestionRepository;

//    @GetMapping("/qna")
//    public String showQnA(Model model) {
//        log.info("QnAController.showQnA");
//
//        return "qna/show";
//    }


}
