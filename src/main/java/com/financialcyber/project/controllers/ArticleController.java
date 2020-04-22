package com.financialcyber.project.controllers;


import com.financialcyber.project.entity.Article;
import com.financialcyber.project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Article> get(@PathVariable Integer id){
        return articleRepository.findById(id);
    }
}
