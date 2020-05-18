package com.financialcyber.project.services;

import com.financialcyber.project.entity.Article;
import com.financialcyber.project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Optional<Article> findById(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public Long count() {
        return articleRepository.count();
    }
}
