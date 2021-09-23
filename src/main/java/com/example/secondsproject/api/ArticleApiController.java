package com.example.secondsproject.api;

import com.example.secondsproject.dto.ArticleForm;
import com.example.secondsproject.entity.Article;
import com.example.secondsproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

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

    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form) {
        log.info(form.toString());

        Article article = form.toEntity();
        Article target = repository.findById(id).orElse(null);
        Article saved;

        if(target == null) {
            saved = repository.save(article);
        } else {
            target.setTitle(article.getTitle());
            target.setContent(article.getContent());
            saved = repository.save(target);
        }

        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id) {
        Article entity = repository.findById(id).orElseThrow( // 만약 없다면,
                () -> new IllegalArgumentException("해당 Article이 없습니다.") // 에러를 만들어 던진다.
        );

        return new ArticleForm(entity);
    }

    @GetMapping("/api/articles")
    public ArrayList getArticles() {
        ArrayList list = new ArrayList();

        Iterable<Article> articleList = repository.findAll();
        Iterator<Article> it = articleList.iterator();
        while (it.hasNext()) {
            Article article = it.next();
            list.add(new ArticleForm(article));
        }
        return list;
    }

}
