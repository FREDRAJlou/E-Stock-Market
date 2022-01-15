package com.stockMarket.services;

import com.stockMarket.entities.Company;
import com.stockMarket.model.CompanyResponse;
import com.stockMarket.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${stock.serviceUrl}")
    private String stockServiceUrl;


    @Autowired
    private RestTemplate restTemplate;

    public CompanyResponse saveCompanies(List<Company> companies) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setMessage("Error");
        companyResponse.setResultCode(false);
        try {
            for (Company company : companies) {
                if(company.getTurnOver()<=10){
                    companyResponse.setMessage("Validation failed: Company turnover("+company.getTurnOver()+"Cr) should be greater than 10Cr to register");
                    return companyResponse;
                }
                company.setDate(new Date());
            }
            this.companyRepository.saveAll(companies);
            companyResponse.setMessage("Success: Company Details Saved  - "+companies.stream().map(company -> company.getCompanyName()).collect(Collectors.joining(",")));
            companyResponse.setResultCode(true);
            logger.info("Saved companies in Company Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return companyResponse;
    }

    public CompanyResponse deleteCompany(String companyCode) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setMessage("Error");
        companyResponse.setResultCode(false);
        try {
            List<Company> companies = this.companyRepository.findByCompanyCode(companyCode);
            for (Company company : companies) {
                this.restTemplate.delete(this.stockServiceUrl+"delete/"+companyCode);
                this.companyRepository.delete(company);
            }
            companyResponse.setMessage("Success: Company Details Deleted - "+companies.stream().map(company -> company.getCompanyName()).collect(Collectors.joining(",")));
            companyResponse.setResultCode(true);
            logger.info("Deleted companies in Company Service ");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return companyResponse;
    }
}