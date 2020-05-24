package com.financialcyber.project.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleTest {


    @Test
    public void getArticleTitleTest() {
        Article articleTest = new Article("articleTitle", "webURL", "webID", LocalDateTime.now());
        assertEquals("articleTitle", articleTest.getArticleTitle());

    }

    @Test
    public void getWebURLTest() {
        Article articleTest = new Article("articleTitle", "webURL", "webID", LocalDateTime.now());
        assertEquals("webURL", articleTest.getWebURL());
    }

    @Test
    public void getWebIDTest() {
        Article articleTest = new Article("articleTitle", "webURL", "webID", LocalDateTime.now());
        assertEquals("webID", articleTest.getWebID());
    }

    @Test
    public void getArticleDateTest() {
        LocalDateTime dateTest = LocalDateTime.now();
        Article articleTest = new Article("articleTitle", "webURL", "webID", dateTest);
        assertEquals(dateTest, articleTest.getArticleDate());
    }
}