package com.example.bankApp.customer.core.dto;

import com.example.bankApp.customer.entity.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private int id;
    private AddressType addressType;
    private String country;
    private String city;
    private String state;
    private String district;
    private String streetNumber;
}
