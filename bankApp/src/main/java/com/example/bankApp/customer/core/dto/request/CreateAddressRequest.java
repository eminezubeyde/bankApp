package com.example.bankApp.customer.core.dto.request;

import com.example.bankApp.customer.entity.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
    private AddressType addressType;
    private String country;
    private String city;
    private String state;
    private String district;
    private String streetNumber;
}
