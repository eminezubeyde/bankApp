package com.example.bankApp.account.entity.base;

import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.entity.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ibanNo;
    private String accountNo;
    private String accountName;
    private BigDecimal balance=BigDecimal.ZERO;
    private BigDecimal lockedBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private LocalDateTime createdAt;


}
