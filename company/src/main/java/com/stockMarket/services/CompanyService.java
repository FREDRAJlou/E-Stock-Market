package com.stockMarket.services;

import com.stockMarket.entities.Company;
import com.stockMarket.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getCompanies(){
		return companyRepository.findAll();
	}

	public void saveCompanies(List<Company> companies){
		for(Company company : companies)
			company.setDate(new Date());
		this.companyRepository.saveAll(companies);
	}
}