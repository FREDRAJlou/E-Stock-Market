package com.stockMarket.repositories;

import java.util.Date;
import java.util.List;

import com.stockMarket.entities.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

	@Query("{companyCode: ?0 , date : {$gt : ?1, $lt : ?2}}")
	List<Stock> findByCompany(String companyCode,Date startDate, Date endDate);

	List<Stock> findByCompanyCode(String companyCode);


}
