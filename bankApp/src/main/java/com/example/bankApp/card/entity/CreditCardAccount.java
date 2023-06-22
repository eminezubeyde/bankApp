package com.example.bankApp.card.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

@Entity
@Getter
@Setter
public class CreditCardAccount {
    @Id
    //GeneratedValue()
    private int id;
    private BigDecimal totalCreditLimit;
    private BigDecimal availableBalance;
    private BigDecimal lastExtractDebt = BigDecimal.ZERO;// son ekstreden kalan borç
    private BigDecimal totalDebt; // güncel borç

    @Temporal(TemporalType.DATE)
    private Date cutOffDate;// hesap kesim tarihi

    @Temporal(TemporalType.DATE)
    private Date paymentDate;// ödeme tarihi


}
