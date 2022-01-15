package com.stockMarket.tests;

import com.stockMarket.StockMarketApplication;
import com.stockMarket.model.StockModel;
import com.stockMarket.repositories.StockRepository;
import com.stockMarket.services.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = StockMarketApplication.class)
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    @Test
    void testGetStocks(){
         when(stockRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertNotNull(this.stockService.getStocks());
    }



}
