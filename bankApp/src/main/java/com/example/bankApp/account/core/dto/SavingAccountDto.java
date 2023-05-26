package com.example.bankApp.account.core.dto;

import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.entity.enums.CurrencyType;
import com.example.bankApp.account.entity.enums.Maturity;
import com.example.bankApp.account.entity.enums.PurposeSaving;
import com.example.bankApp.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class SavingAccountDto {
    private int id;
    private int checkingAccountId;
    private String accountName;
    private String ibanNo;
    private String accountNo;
    private double balance;
    private double lockedBalance;
    private CurrencyType currencyType;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private double successRate;
    private double targetAmount;
    private double openingBalance;
    private Maturity maturity;
    private Date maturityDate;
    private PurposeSaving purposeSaving;


}
