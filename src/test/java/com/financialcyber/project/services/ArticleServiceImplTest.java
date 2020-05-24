package com.financialcyber.project.services;

import com.financialcyber.project.entity.Article;
import com.financialcyber.project.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ArticleServiceImplTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService = new ArticleServiceImpl();

    @Test
    public void countTest() {
        when(articleRepository.count()).thenReturn((long) 5);

        long result = articleService.count();
        assertEquals(5, result);
    }

    @Test
    public void findbyIdDBTest(){
        Article articleMock = new Article("title", "web", "webId", LocalDateTime.now());
        when(articleRepository.findById(1)).thenReturn(java.util.Optional.of(articleMock));

        Optional<Article> article = articleService.findById(1);
        assertEquals(articleMock, article.get());
    }
}