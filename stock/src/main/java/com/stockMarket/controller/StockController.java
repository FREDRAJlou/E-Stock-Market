package com.stockMarket.controller;


import com.stockMarket.entities.Stock;
import com.stockMarket.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stocks")
public class StockController {

	@Autowired
	private StockService stockService;


	@GetMapping("getAllStocks")
//	@Cacheable(value = "stocks")
	public List<Stock> getAllAirlines() {
		return this.stockService.getStocks();
	}

	@PostMapping("saveStocks")
//	@Cacheable(value = "stocks")
	public void saveStocks(@RequestBody List<Stock> stocks) {
		 this.stockService.saveStocks(stocks);
	}

}
