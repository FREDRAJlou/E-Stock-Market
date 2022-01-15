package com.stockMarket.controller;


import com.stockMarket.entities.Stock;
import com.stockMarket.model.StockResponse;
import com.stockMarket.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@GetMapping("/")
	public String healthCheck(){
		return "Healthy";
	}

	@PostMapping("add/{companyCode}")
	@ApiOperation(value = "save Stock Details", notes = "save stock Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Stocks saved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while saving stocks")})
	public StockResponse saveStocks(@RequestBody @Valid List<Stock> stocks, @PathVariable("companyCode") String companyCode) {
		logger.info("Got a POST request to Save Stock Details");
		return this.stockService.saveStocks(stocks,companyCode);
	}

    @DeleteMapping("delete/{companyCode}")
    @ApiOperation(value = "delete Stock Details", notes = "delete stock Details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Stocks deleted successfully"),
            @ApiResponse(code = 400, message = "Exception occurred while deleting stocks")})
    public StockResponse saveStocks(@PathVariable("companyCode") String companyCode) {
        logger.info("Got a DELETE request to delete Stock Details for comapny code - "+ companyCode);
        return this.stockService.deleteStocks(companyCode);
    }

}
