package com.financialcyber.project.repository;

import com.financialcyber.project.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
