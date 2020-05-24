package com.financialcyber.project.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StockTest {

    @Test
    public void getStockNameTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2f, 1.1, 1.0, 2.2);
        assertEquals("stockName", stockTest.getStockName());
    }

    @Test
    public void getDateTest() {
        LocalDateTime dateTest = LocalDateTime.now();
        Stock stockTest = new Stock("stockName", dateTest, 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(dateTest, stockTest.getDate());

    }

    @Test
    public void getVolumeTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(3, stockTest.getVolume());
    }

    @Test
    public void getHighTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(1.2, stockTest.getHigh(),0);
    }

    @Test
    public void getLowTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(1.1, stockTest.getLow(), 0);
    }

    @Test
    public void getCloseTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(1.0, stockTest.getClose(), 0);
    }

    @Test
    public void getOpenTest() {
        Stock stockTest = new Stock("stockName", LocalDateTime.now(), 3, 1.2, 1.1, 1.0, 2.2);
        assertEquals(2.2, stockTest.getOpen(), 0);
    }
}