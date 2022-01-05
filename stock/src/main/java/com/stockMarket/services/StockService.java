package com.stockMarket.services;

import com.stockMarket.adapter.StockDomainAdapter;
import com.stockMarket.entities.Stock;
import com.stockMarket.model.AggregatorResult;
import com.stockMarket.model.StockModel;
import com.stockMarket.model.StockResponse;
import com.stockMarket.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public StockResponse getStocks() {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setMessage("Error");
        stockResponse.setResultCode(false);
        try {
            List<StockModel> stocks = StockDomainAdapter.covertToStockDomainList(this.stockRepository.findAll());
            stockResponse.setMessage("Success: Stocks retrieved");
            stockResponse.setResultCode(true);
            stockResponse.setStockList(stocks);
            logger.info("Got details in Stock Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return stockResponse;
    }

    public StockResponse getStocks(String companyCode, String fromDate, String toDate) {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setMessage("Error");
        stockResponse.setResultCode(false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date from, to;
        try {
            from = simpleDateFormat.parse(fromDate);
            to = simpleDateFormat.parse(toDate);
        } catch (Exception ex) {
            logger.error("Exception while parsing Date" + ex.getLocalizedMessage());
            stockResponse.getMessage().concat(ex.getMessage());
            return stockResponse;
        }
        try {
            List<StockModel> stocks = StockDomainAdapter.covertToStockDomainList(this.stockRepository.findByCompany(companyCode, from, to));
            Aggregation avgAggr = Aggregation.newAggregation(Aggregation.match(Criteria.where("companyCode").is(companyCode).and("date").lt(to).gt(from)), Aggregation.group("companyCode").avg("price").as("avg").min("price").as("min").max("price").as("max"));
            AggregationResults<AggregatorResult> aggregatorResults = mongoTemplate.aggregate(avgAggr, "STOCK", AggregatorResult.class);
            stockResponse.setMessage(stocks.isEmpty() ? "NO Stocks available" : "Success: Stocks retrieved");
            stockResponse.setResultCode(true);
            stockResponse.setStockList(stocks);
            if (Objects.nonNull(aggregatorResults) && !aggregatorResults.getMappedResults().isEmpty()) {
                AggregatorResult result = aggregatorResults.getMappedResults().get(0);
                if (Objects.nonNull(result)) {
                    stockResponse.setAveragePrice(result.getAvg());
                    stockResponse.setMinPrice(result.getMin());
                    stockResponse.setMaxPrice(result.getMax());
                }
            }
            logger.info("Got details in Stock Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return stockResponse;
    }

    public StockResponse getStocksByCompanyCode(String companyCode) {
        StockResponse stockResponse = new StockResponse();
        stockResponse.setMessage("Error");
        stockResponse.setResultCode(false);
        Date from, to;
        try {
            List<StockModel> stocks = StockDomainAdapter.covertToStockDomainList(this.stockRepository.findByCompanyCode(companyCode));
            stockResponse.setMessage(stocks.isEmpty() ? "NO Stocks available" : "Success: Stocks retrieved");
            stockResponse.setResultCode(true);
            stockResponse.setStockList(stocks);
            logger.info("Got details in Stock Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return stockResponse;
    }

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