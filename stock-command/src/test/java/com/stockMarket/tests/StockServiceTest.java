package com.stockMarket.tests;

import com.stockMarket.StockMarketCommandApplication;
import com.stockMarket.entities.Stock;
import com.stockMarket.repositories.StockRepository;
import com.stockMarket.services.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = StockMarketCommandApplication.class)
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;


    @Test
    void testSaveStocks(){
        List<Stock> stockList =List.of(new Stock());
        when(stockRepository.saveAll(any())).thenReturn(stockList);
        this.stockService.saveStocks(stockList,"CC");
        verify(this.stockRepository,times(1)).saveAll(stockList);
    }

    @Test
    void testDeleteStocks(){
        doNothing().when(this.stockRepository).delete(any());
        when(this.stockRepository.findByCompanyCode(anyString())).thenReturn(List.of(new Stock()));
        this.stockService.deleteStocks("CC");
        verify(this.stockRepository,times(1)).delete(any());
    }

}
