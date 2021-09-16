package com.example.secondsproject.controller;

import com.example.secondsproject.dto.ArticleForm;
import com.example.secondsproject.entity.Article;
import com.example.secondsproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor // final 필드 값을 알아서 가져옴! (@autowired 대체!)
@Controller
public class ArticleController {

    // 리파지터리 객체 자동 삽입 됨! 위에서 @RequiredArgsConstructor 했음!
    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model) {
        // 모든 article을 가져옴
        // Iterable 인터페이스는 ArrayList의 부모 인터페이스
        Iterable<Article> articleList = articleRepository.findAll();

        // 뷰페이지로 articles를 모델에 넣어 전달.
        model.addAttribute("articles", articleList);

        // 뷰페이지 설정.
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles")
    public String create(ArticleForm form) {
        log.info(form.toString());
        return "redirect:/articles";
    }

}
