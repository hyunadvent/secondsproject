package com.example.secondsproject.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder // 빌더 패턴 적용! 추후 설명..!
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void rewrite(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
