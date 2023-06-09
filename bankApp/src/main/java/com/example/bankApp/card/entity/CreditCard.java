package com.example.bankApp.card.entity;

import com.example.bankApp.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;
    private String cvv;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCardAccount creditCardAccount;


    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
