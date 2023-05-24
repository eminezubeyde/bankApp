package com.example.bankApp.customer.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String identityNumber;
    private String name;
    private String surname;
    private String password;
    private String telephone;
    private BigDecimal income;
    private Date birthDay;
}
