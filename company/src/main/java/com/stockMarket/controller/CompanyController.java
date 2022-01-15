package com.stockMarket.controller;


import com.stockMarket.model.CompanyResponse;
import com.stockMarket.services.CompanyService;
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
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("/")
	public String healthCheck(){
		return this.applicationName+" - Healthy";
	}


	@GetMapping("info/{companyCode}")
	@ApiOperation(value = "get Company Details", notes = "retrieves Company Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Company Details retrieved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while retrieving Company Details")})
	public CompanyResponse getCompanyDetails(@PathVariable("companyCode") String companyCode) {
		logger.info("Got a Get request to retrieve company Details for company code - "+companyCode);
		return this.companyService.getCompanyByCompanyCode(companyCode);
	}


	@GetMapping("getAll")
	@ApiOperation(value = "get Company Details", notes = "retrieves all Company Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Company Details retrieved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while retrieving Company Details")})
	public CompanyResponse getAllCompanyDetails() {
		logger.info("Got a Get request to retrieve all company Details");
		return this.companyService.getCompanies();
	}

}
