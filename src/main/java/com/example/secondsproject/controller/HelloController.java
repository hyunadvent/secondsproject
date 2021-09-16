package com.example.secondsproject.controller;

import com.example.secondsproject.entity.Article;
import com.example.secondsproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor // final 필드 값을 알아서 가져옴! (@autowired 대체!)
public class HelloController {
    // 리파지터리 객체 자동 삽입 됨! 위에서 @RequiredArgsConstructor 했음!
    private final ArticleRepository articleRepository;

    @GetMapping("/")
    public String hello() {
        return "helloworld";
    }

    @GetMapping("/init")
    public String init() {
        Article temp1 = Article.builder().id(1L).content("AAA").title("제목-A").build();
        Article temp2 = Article.builder().id(2L).content("BBB").title("제목-BBB").build();
        Article temp3 = Article.builder().id(3L).content("CCC").title("제목-CCC").build();

        Article saved1 = articleRepository.save(temp1);
        Article saved2 = articleRepository.save(temp2);
        Article saved3 = articleRepository.save(temp3);

        log.info(saved1.toString());
        log.info(saved2.toString());
        log.info(saved3.toString());

        return "redirect:/articles";
    }
}
