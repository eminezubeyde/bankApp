package com.example.bankApp.account.core.dto.requests;

import com.example.bankApp.account.entity.enums.CurrencyType;
import com.example.bankApp.account.entity.enums.Maturity;
import com.example.bankApp.account.entity.enums.PurposeSaving;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SavingAccountRequest {
    private int checkingAccountId;
    private String accountName;
    private CurrencyType currencyType;
    private PurposeSaving purposeSaving;
    private double targetAmount;
    private Maturity maturity;
    private double openingBalance;
    private Date maturityDate;
}
