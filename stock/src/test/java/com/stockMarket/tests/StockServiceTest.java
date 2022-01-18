package com.stockMarket.tests;

import com.stockMarket.StockMarketApplication;
import com.stockMarket.entities.Stock;
import com.stockMarket.model.AggregatorResult;
import com.stockMarket.model.StockModel;
import com.stockMarket.repositories.StockRepository;
import com.stockMarket.services.StockService;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = StockMarketApplication.class)
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testGetStocks(){
         when(stockRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertNotNull(this.stockService.getStocks());
    }

    @Test
    void testGetStocksByCompanyCode(){
        when(stockRepository.findByCompanyCode(anyString())).thenReturn(List.of(new Stock()));
        Assertions.assertNotNull(this.stockService.getStocksByCompanyCode("test"));
    }


}
