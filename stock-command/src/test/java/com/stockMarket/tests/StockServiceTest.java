package com.stockMarket.tests;

import com.stockMarket.StockMarketCommandApplication;
import com.stockMarket.repositories.StockRepository;
import com.stockMarket.services.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

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
        when(stockRepository.saveAll(any())).thenReturn(Collections.emptyList());
        this.stockService.saveStocks(Collections.emptyList(),"CC");
        verify(this.stockRepository,times(1)).saveAll(Collections.emptyList());
    }

}
