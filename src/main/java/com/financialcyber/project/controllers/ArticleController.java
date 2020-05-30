package com.financialcyber.project.controllers;


import com.financialcyber.project.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(path="/count")
    public Long getCount(){
        return articleService.count();
    }

}
