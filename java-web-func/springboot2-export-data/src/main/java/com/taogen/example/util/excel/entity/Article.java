package com.taogen.example.util.excel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Taogen
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Article {
    private Integer id;
    private String site;
    private String publishTime;
    private String title;
    List<Error> errors;

    public static List<Article> getData() {
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder()
                .id(1)
                .site("百度")
                .publishTime("2018-01-01")
                .title("百度百科")
                .errors(Arrays.asList(
                        Error.builder()
                                .errorType("严重错误")
                                .errorWords(Arrays.asList("百度error1", "百度error2", "百度error3"))
                                .build(),
                        Error.builder()
                                .errorType("错误")
                                .errorWords(Arrays.asList("baidu", "baidua", "adsf"))
                                .build()))
                .build());
        articles.add(Article.builder()
                .id(2)
                .site("网易")
                .publishTime("2018-01-02")
                .title("网易百科")
                .errors(Arrays.asList(
                        Error.builder()
                                .errorType("严重错误")
                                .errorWords(Arrays.asList("网易百科", "网易百科", "网易百科"))
                                .build()
                ))
                .build());
        return articles;
    }
}
