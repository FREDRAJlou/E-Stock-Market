package com.stockMarket.tests;

import com.stockMarket.CompanyManagementApplication;
import com.stockMarket.entities.Company;
import com.stockMarket.model.StockModel;
import com.stockMarket.model.StockResponse;
import com.stockMarket.repositories.CompanyRepository;
import com.stockMarket.services.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CompanyManagementApplication.class)
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetCompanies(){
         when(companyRepository.findAll()).thenReturn(List.of(new Company()));
        StockResponse stockResponse = new StockResponse();
        stockResponse.setStockList(List.of(new StockModel()));
        ResponseEntity<Object> responseEntity = ResponseEntity.of(Optional.of(stockResponse));
         when( this.restTemplate.getForEntity(anyString(),any())).thenReturn(responseEntity);
        Assertions.assertNotNull(this.companyService.getCompanies().getCompanyList());
    }

    @Test
    void testGetCompanyByCompanyCode(){
        when(companyRepository.findByCompanyCode(anyString())).thenReturn(List.of(new Company()));
        StockResponse stockResponse = new StockResponse();
        stockResponse.setStockList(List.of(new StockModel()));
        ResponseEntity<Object> responseEntity = ResponseEntity.of(Optional.of(stockResponse));
        when( this.restTemplate.getForEntity(anyString(),any())).thenReturn(responseEntity);
        Assertions.assertNotNull(this.companyService.getCompanyByCompanyCode("Test").getCompanyList());
    }

}
