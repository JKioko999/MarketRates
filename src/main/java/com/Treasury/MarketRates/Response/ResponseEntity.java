package com.Treasury.MarketRates.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private String message;
    private T entity;
    private Integer StatusCode;
}
