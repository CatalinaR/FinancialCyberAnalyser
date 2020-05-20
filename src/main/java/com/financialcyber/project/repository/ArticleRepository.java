package com.financialcyber.project.repository;

import com.financialcyber.project.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    Optional<Article> findById(Integer id);

}
