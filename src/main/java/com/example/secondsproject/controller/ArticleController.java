package com.example.secondsproject.controller;

import com.example.secondsproject.dto.ArticleForm;
import com.example.secondsproject.entity.Article;
import com.example.secondsproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) { // url의 {id} 값을 변수화
        // id를 통해 Article을 가져옴
        Article article = articleRepository.findById(id).orElse(null);

        // article을 뷰 페이지로 전달
        model.addAttribute("article", article);
        return "articles/show";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        model.addAttribute("article", article);
        return "articles/edit";
    }

}
