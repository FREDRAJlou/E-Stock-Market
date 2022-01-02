package com.stockMarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockModel {
    private String id;
    private Double price;
    private String companyCode;
    private Date date;

}
