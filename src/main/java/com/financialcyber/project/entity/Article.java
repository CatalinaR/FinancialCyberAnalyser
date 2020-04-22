package com.financialcyber.project.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="article_id")
    private Integer id;

    @Column(name="title")
    private String articleTitle;

    @Column(name="web_URL")
    private  String webURL;

    @Column(name="web_id")
    private  String webID;

    @Column(name="article_date")
    private LocalDateTime articleDate;

    public Article(){

    }


    public Article(String articleTitle, String webURL, String webID, LocalDateTime articleDate){
        this.articleTitle = articleTitle;
        this.webURL = webURL;
        this.webID = webID;
        this.articleDate = articleDate;
    }

    public Integer getId() {
        return id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getWebID() {
        return webID;
    }

    public LocalDateTime getArticleDate() {
        return articleDate;
    }

}
