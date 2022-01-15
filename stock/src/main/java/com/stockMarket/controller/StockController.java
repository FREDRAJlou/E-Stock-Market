package com.stockMarket.controller;


import com.stockMarket.model.StockResponse;
import com.stockMarket.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String healthCheck(){
        return this.applicationName+" - Healthy";
    }


	@GetMapping("getAllStocks")
	@ApiOperation(value = "get Stock Details", notes = "retrieves all available stocks")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Stocks retrieved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while retrieving stocks")})
	public StockResponse getAllStocks() {
		logger.info("Got a GET request for Stock Details");
		return this.stockService.getStocks();
	}

    @GetMapping("get/{companyCode}/{startDate}/{endDate}")
    @ApiOperation(value = "get Stock Details", notes = "retrieves available stocks for the given company code in given time frame")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Stocks retrieved successfully"),
            @ApiResponse(code = 400, message = "Exception occurred while retrieving stocks")})
    public StockResponse getStocksByCompanyCodeAndRandge(@PathVariable("companyCode")String companyCode, @PathVariable("startDate") String startDate, @PathVariable("endDate")String endDate) {
        logger.info("Got a GET request for Stock Details for company Code - " + companyCode+" from - "+ startDate +" - to - " +endDate);
        return this.stockService.getStocks(companyCode,startDate,endDate);
    }

    @GetMapping("get/{companyCode}")
    @ApiOperation(value = "get Stock Details", notes = "retrieves available stocks for the given company code")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Stocks retrieved successfully"),
            @ApiResponse(code = 400, message = "Exception occurred while retrieving stocks")})
    public StockResponse getStocksByCompanyCode(@PathVariable("companyCode")String companyCode) {
        logger.info("Got a GET request for Stock Details for company Code - " + companyCode);
        return this.stockService.getStocksByCompanyCode(companyCode);
    }

}
