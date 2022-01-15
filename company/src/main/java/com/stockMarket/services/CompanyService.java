package com.stockMarket.services;

import com.stockMarket.adapter.CompanyDomainAdapter;
import com.stockMarket.model.CompanyModel;
import com.stockMarket.model.CompanyResponse;
import com.stockMarket.model.StockResponse;
import com.stockMarket.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Value("${stock.serviceUrl}")
    private String stockServiceUrl;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RestTemplate restTemplate;

    public CompanyResponse getCompanies() {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setMessage("Error");
        companyResponse.setResultCode(false);
        try {
            List<CompanyModel> companyModels = CompanyDomainAdapter.covertToCompanyDomainList(companyRepository.findAll());
            companyResponse.setMessage(companyModels.isEmpty() ? "No Company detail available" : "Success: company details retrieved");
            companyResponse.setResultCode(true);
            for(CompanyModel companyModel : companyModels){
                ResponseEntity<StockResponse> stockResponse = this.restTemplate.getForEntity(stockServiceUrl+"get/"+companyModel.getCompanyCode(), StockResponse.class);
                companyModel.setStocks(stockResponse.getBody().getStockList());
            }
            companyResponse.setCompanyList(companyModels);
            logger.info("Got details in Company Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return companyResponse;
    }

    public CompanyResponse getCompanyByCompanyCode(String companyCode) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setMessage("Error");
        companyResponse.setResultCode(false);
        try {
            List<CompanyModel> companyModels = CompanyDomainAdapter.covertToCompanyDomainList(this.companyRepository.findByCompanyCode(companyCode));
            companyResponse.setMessage("Success: company details retrieved");
            companyResponse.setResultCode(true);
            for(CompanyModel companyModel : companyModels){
                ResponseEntity<StockResponse> stockResponse = this.restTemplate.getForEntity(this.stockServiceUrl+"get/"+companyModel.getCompanyCode(), StockResponse.class);
                companyModel.setStocks(stockResponse.getBody().getStockList());
            }
            companyResponse.setCompanyList(companyModels);
            logger.info("Got details in Company Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return companyResponse;
    }

}