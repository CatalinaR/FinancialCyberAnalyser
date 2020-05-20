package com.financialcyber.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialcyber.project.entity.Article;
import com.financialcyber.project.services.ArticleService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCountTest() throws Exception {

        when(articleService.count()).thenReturn((long) 5);

        mockMvc.perform(get("/api/article/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

    }
}