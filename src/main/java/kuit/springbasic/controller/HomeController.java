package kuit.springbasic.controller;

import kuit.springbasic.dao.QuestionDao;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;//자동 주입
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller// 컨트롤러임을 알 수 있게하는 어노테이션
@RequiredArgsConstructor // 생성자 주입이 까다로운데 해당 어노테이션을 통해 생성자를 따로 만들필요가 없어져 주입 문제도 해결된다.

public class HomeController {

    private final QuestionDao questionDao;

    @GetMapping("/")
    public String showHome(Model model) {
        log.info("HomeController.home");

        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }


}
