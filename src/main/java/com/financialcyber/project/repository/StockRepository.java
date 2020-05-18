package com.financialcyber.project.repository;

import com.financialcyber.project.entity.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Optional<Stock> findById(Integer id);
}
