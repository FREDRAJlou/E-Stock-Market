package com.stockMarket.adapter;

import com.stockMarket.entities.Company;
import com.stockMarket.model.CompanyModel;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CompanyDomainAdapter {
    public CompanyModel covertToCompanyDomain(Company company){
        return CompanyModel.builder().id(company.getId())
                .companyCode(company.getCompanyCode())
                .date(company.getDate())
                .ceo(company.getCeo())
                .companyName(company.getCompanyName())
                .stockExchange(company.getStockExchange())
                .turnOver(company.getTurnOver())
                .website(company.getWebsite())
                .build();
    }

    public List<CompanyModel>covertToCompanyDomainList(List<Company> company){
        return company.stream().map(company1 -> covertToCompanyDomain(company1)).collect(Collectors.toList());
    }
}
