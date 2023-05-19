package com.example.bankApp.entity;

import com.example.bankApp.entity.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private AddressType addressType;
    private String country;
    private String city;
    private String state;
    private String district;
    private String streetNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
