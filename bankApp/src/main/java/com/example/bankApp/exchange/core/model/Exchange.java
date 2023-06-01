
package com.example.bankApp.exchange.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exchange {
    private String date;
    private String historical;
    private Info info;
    private Query query;
    private Double result;
    private Boolean success;

}


