package com.stockMarket.services;

import com.stockMarket.entities.Stock;
import com.stockMarket.model.StockResponse;
import com.stockMarket.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    public StockResponse saveStocks(List<Stock> stocks, String companyCode) {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setMessage("Error");
        stockResponse.setResultCode(false);
        try {
            for (Stock stock : stocks) {
                stock.setCompanyCode(companyCode);
                Date now = new Date();
                stock.setDate(now);
                stock.setOffset(now.getTimezoneOffset());
            }
            this.stockRepository.saveAll(stocks);
            stockResponse.setMessage("Success: Stock Created");
            stockResponse.setResultCode(true);
            logger.info("Got details in Stock Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return stockResponse;
    }

    public StockResponse deleteStocks(String companyCode) {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setMessage("Error");
        stockResponse.setResultCode(false);
        try {
            List<Stock> stocks = this.stockRepository.findByCompanyCode(companyCode);
            for (Stock stock : stocks)
                this.stockRepository.delete(stock);
            stockResponse.setMessage("Success: Stocks deleted for company - " + companyCode);
            stockResponse.setResultCode(true);
            logger.info("deleted stock details for company " + companyCode);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return stockResponse;
    }
}