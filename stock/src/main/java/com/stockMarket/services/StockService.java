package com.stockMarket.services;

import com.stockMarket.entities.Stock;
import com.stockMarket.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public List<Stock> getStocks(){
		return stockRepository.findAll();
	}

	public void saveStocks(List<Stock> stocks){
		for(Stock stock : stocks)
			stock.setDate(new Date());
		this.stockRepository.saveAll(stocks);
	}
}