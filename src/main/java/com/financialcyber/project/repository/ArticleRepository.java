package com.financialcyber.project.repository;

import com.financialcyber.project.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Optional<Article> findById(Integer id);

}
