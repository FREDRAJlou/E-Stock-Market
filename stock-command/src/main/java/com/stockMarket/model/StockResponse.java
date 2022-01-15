package com.stockMarket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockResponse {
    private Boolean resultCode;
    private String message;
}
