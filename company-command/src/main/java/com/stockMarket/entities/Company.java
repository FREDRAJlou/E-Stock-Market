package com.stockMarket.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document("COMPANY")
@Data
public class Company {

	@Id
	private String id;
	private Date date;
	private String companyCode;
	private String companyName;
	private String ceo;
	private String stockExchange;
	private Long turnOver;
	private String website;

}
