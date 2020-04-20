package com.financialcyber.project.repository;

import com.financialcyber.project.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
