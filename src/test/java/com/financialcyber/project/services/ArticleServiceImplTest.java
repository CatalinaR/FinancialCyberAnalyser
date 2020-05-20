package com.financialcyber.project.services;

import com.financialcyber.project.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ArticleServiceImplTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService = new ArticleServiceImpl();

    @Test
    public void count() {
        when(articleRepository.count()).thenReturn((long) 5);

        long result = articleService.count();
        System.out.println("Result: " + result);
        assertEquals(5, result);
    }
}