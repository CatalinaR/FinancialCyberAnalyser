package com.financialcyber.project.controllers;


import com.financialcyber.project.entity.Article;
import com.financialcyber.project.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(path="/count")
    public Long getCount(){
        return articleService.count();
    }

//    @GetMapping(path="{id}")
//    public Optional<Article> getId(@PathVariable String id){
//        return articleService.findById(Integer.valueOf(id));
//    }
}
