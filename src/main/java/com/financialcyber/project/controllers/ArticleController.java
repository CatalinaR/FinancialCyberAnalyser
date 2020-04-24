package com.financialcyber.project.controllers;


import com.financialcyber.project.entity.Article;
import com.financialcyber.project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path="{id}")
    public Optional<Article> get(@PathVariable Integer id){
        return articleRepository.findById(id);
    }
}
