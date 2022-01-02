package com.stockMarket.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class CompanyModel {
    private String id;
    private Date date;
    private String companyCode;
    private String companyName;
    private String ceo;
    private String stockExchange;
    private Long turnOver;
    private String website;
    private List<StockModel> stocks;
}
