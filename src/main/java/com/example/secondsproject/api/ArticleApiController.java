package com.example.secondsproject.api;

import com.example.secondsproject.dto.ArticleForm;
import com.example.secondsproject.entity.Article;
import com.example.secondsproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository repository;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form) {
        log.info(form.toString()); // 받아온 데이터 확인!

        // dto를 entity로 변경
        Article article = form.toEntity();

        // 레파지토리에게 전달
        Article saved = repository.save(article);
        log.info(saved.toString());

        // 저장 엔티티의 id 값 반환!
        return saved.getId();
    }
}
