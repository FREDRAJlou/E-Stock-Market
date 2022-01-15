package com.stockMarket.controller;


import com.stockMarket.entities.Company;
import com.stockMarket.model.CompanyResponse;
import com.stockMarket.services.CompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

	@DeleteMapping("delete/{companyCode}")
	@ApiOperation(value = "delete Company Details", notes = "delete Company Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Company Details deleted successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while deleting Company Details")})
	public CompanyResponse deleteCompanyDetails(@PathVariable("companyCode") String companyCode) {
		logger.info("Got a delete request to delete company Details for company code - "+companyCode);
		return this.companyService.deleteCompany(companyCode);
	}

	@PostMapping("register")
	@ApiOperation(value = "save Company Details", notes = "save Company Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Company saved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while saving company details")})
	public CompanyResponse saveCompanies(@RequestBody List<Company> companies) {
		logger.info("Got a Post request to save company Details");
		return this.companyService.saveCompanies(companies);
	}

}
