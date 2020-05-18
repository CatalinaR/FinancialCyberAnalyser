package com.financialcyber.project.services;

import com.financialcyber.project.entity.Article;

import java.util.Optional;

public interface ArticleService {

    Optional<Article> findById(Integer id);

    Long count();
}
