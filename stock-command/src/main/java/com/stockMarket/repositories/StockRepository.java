package com.stockMarket.repositories;

import com.stockMarket.entities.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    List<Stock> findByCompanyCode(String companyCode);
}
