package com.stockMarket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockResponse {
    private Boolean resultCode;
    private String message;
    private List<StockModel> stockList;
}
