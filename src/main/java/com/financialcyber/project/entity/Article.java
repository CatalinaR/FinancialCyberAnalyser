package com.financialcyber.project.entity;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="article_id")
    private Integer id;

    @Column(name="title")
    private String articleTitle;

    @Column(name="web_URL")
    private String webURL;

    @Column(name="web_id")
    private String webID;

    @Column(name="article_date")
    private String articleDate;

    public Article(String articleTitle, String webURL, String webID, String articleDate){
        this.articleTitle = articleTitle;
        this.webURL = webURL;
        this.webID = webID;
        this.articleDate = articleDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public String getWebID() {
        return webID;
    }

    public void setWebID(String webID) {
        this.webID = webID;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }


}
