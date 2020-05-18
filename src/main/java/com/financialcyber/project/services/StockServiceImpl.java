package com.financialcyber.project.services;

import com.financialcyber.project.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;


    @Override
    public Long count() {
        return stockRepository.count();
    }
}
