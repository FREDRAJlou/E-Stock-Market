package com.stockMarket.adapter;

import com.stockMarket.entities.Stock;
import com.stockMarket.model.StockModel;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class StockDomainAdapter {
    public StockModel covertToStockDomain(Stock stock){
        return StockModel.builder().id(stock.getId()).companyCode(stock.getCompanyCode()).date(Objects.nonNull(stock.getOffset())?new Date(stock.getDate().getTime()-60000*stock.getOffset()):stock.getDate()).price(stock.getPrice()).build();
    }

    public List<StockModel> covertToStockDomainList(List<Stock> stock){
        return stock.stream().map(stock1 -> covertToStockDomain(stock1)).collect(Collectors.toList());
    }
}
