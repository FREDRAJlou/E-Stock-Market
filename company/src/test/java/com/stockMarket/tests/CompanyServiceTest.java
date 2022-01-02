package com.stockMarket.tests;

import com.stockMarket.CompanyManagementApplication;
import com.stockMarket.repositories.CompanyRepository;
import com.stockMarket.services.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CompanyManagementApplication.class)
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    void testGetCompanies(){
         when(companyRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertNotNull(this.companyService.getCompanies());
    }

    @Test
    void testSaveCompany(){
        when(companyRepository.saveAll(any())).thenReturn(Collections.emptyList());
        this.companyService.saveCompanies(Collections.emptyList())
;        verify(this.companyRepository,times(1)).saveAll(Collections.emptyList());
    }

}
