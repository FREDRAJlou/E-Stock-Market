package com.stockMarket.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class StockModel {
    private String id;
    private Double price;
    private String companyCode;
    private Date date;
}
