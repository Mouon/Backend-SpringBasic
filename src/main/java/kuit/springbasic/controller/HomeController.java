package kuit.springbasic.controller;

import kuit.springbasic.db.MemoryQuestionRepository;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;//자동 주입
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller// 컨트롤러임을 알 수 있게하는 어노테이션
@RequiredArgsConstructor // 생성자 주입이 까다로운데 해당 어노테이션을 통해 생성자를 따로 만들필요가 없어져 주입 문제도 해결된다.

public class HomeController {

    private final MemoryQuestionRepository memoryQuestionRepository;

    //localhost:8080/
//    @RequestMapping("/homeV1")
////    @RequestMapping("/")
//    public ModelAndView showHomeV1(HttpServletRequest request, HttpServletResponse response) {
//        log.info("HomeController.homeV1");
//
//        ModelAndView modelAndView = new ModelAndView("home");
//
//        List<Question> questions = memoryQuestionRepository.findAll();
//        modelAndView.addObject("questions", questions);
//
//        return modelAndView;
//    }
//
//    @RequestMapping("/homeV2")
////    @RequestMapping("/")
//    public ModelAndView showHomeV2() {
//        log.info("HomeController.homeV2");
//
//        ModelAndView modelAndView = new ModelAndView("home");
//
//        List<Question> questions = memoryQuestionRepository.findAll();
//        modelAndView.addObject("questions", questions);
//
//        return modelAndView;
//    }

    @RequestMapping("/")
    public String showHome(Model model) {
        log.info("HomeController.home");

        List<Question> questions = memoryQuestionRepository.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }

}
