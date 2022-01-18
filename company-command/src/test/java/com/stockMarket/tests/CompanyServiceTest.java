package com.stockMarket.tests;

import com.stockMarket.CompanyManagementCommandApplication;
import com.stockMarket.entities.Company;
import com.stockMarket.repositories.CompanyRepository;
import com.stockMarket.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CompanyManagementCommandApplication.class)
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    void testSaveCompany(){
        Company company = new Company();
        company.setTurnOver(100l);
        List<Company> companyList = List.of(company);
        when(companyRepository.saveAll(any())).thenReturn(companyList);
        this.companyService.saveCompanies(companyList);
        verify(this.companyRepository,times(1)).saveAll(companyList);
    }


    @Test
    void testDeleteCompany(){
        doNothing().when(this.companyRepository).delete(any());
        doNothing().when(this.restTemplate).delete(any());
        when(this.companyRepository.findByCompanyCode(anyString())).thenReturn(List.of(new Company()));
        this.companyService.deleteCompany("test");
        verify(this.companyRepository,times(1)).delete(any());
    }

}
