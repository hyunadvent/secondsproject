package com.example.secondsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ArticleForm {
    private String title;
    private String content;
    private String author;
}
