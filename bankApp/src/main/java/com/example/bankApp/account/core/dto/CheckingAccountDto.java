package com.example.bankApp.account.core.dto;

import com.example.bankApp.account.entity.enums.AccountType;
import com.example.bankApp.account.entity.enums.CurrencyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CheckingAccountDto {
    private int id;
    private int customerId;
    private String ibanNo;
    private String accountNo;
    private String accountName;
    private String bankCode;
    private String branchCode;
    private String branchName;
    private double balance;
    private double lockedBalance;
    private CurrencyType currencyType;
    private AccountType accountType;
    private LocalDateTime createdAt;
}
