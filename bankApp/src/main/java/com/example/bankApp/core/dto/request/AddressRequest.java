package com.example.bankApp.core.dto.request;

import com.example.bankApp.entity.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private AddressType addressType;
    private String country;
    private String city;
    private String state;
    private String district;
    private String streetNumber;
}
