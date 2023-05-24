package com.example.bankApp.account.entity;

import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.account.entity.enums.Maturity;
import com.example.bankApp.account.entity.enums.PurposeSaving;
import com.example.bankApp.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingAccount extends Account {//vadeli hesap
    private int id;

    private BigDecimal successRate = BigDecimal.ZERO;//başarı oranı
    private BigDecimal targetAmount = BigDecimal.ZERO;//hedef tutar

    @Enumerated(value = EnumType.ORDINAL)//vade
    private Maturity maturity;

    @Temporal(TemporalType.DATE)
    private Date maturityDate;// vade tarihi

    @Enumerated(EnumType.STRING)//hesap amacı
    private PurposeSaving purposeSaving;

    @OneToOne
    private CheckingAccount parentAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
