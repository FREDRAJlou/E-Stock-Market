package com.stockMarket.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document("STOCK")
@Data
@Getter
@Setter
public class Stock {

	@Id
	private String id;
	private Double price;
	private String companyCode;
	private Date date;
	private int offset;

}
