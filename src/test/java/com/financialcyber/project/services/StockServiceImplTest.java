package com.financialcyber.project.services;

import com.financialcyber.project.repository.StockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StockServiceImplTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockService stockService = new StockServiceImpl();

    @Test
    public void count() {
        when(stockRepository.count()).thenReturn((long) 5);

        long result = stockService.count();
        assertEquals(5, result);
    }
}