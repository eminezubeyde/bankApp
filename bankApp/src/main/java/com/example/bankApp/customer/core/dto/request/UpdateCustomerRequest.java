package com.example.bankApp.customer.core.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerRequest {
    private String surname;
    private String password;
    private String telephone;
}
