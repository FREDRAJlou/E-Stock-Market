package com.stockMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
public class CompanyManagementCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementCommandApplication.class, args);
	}

}
