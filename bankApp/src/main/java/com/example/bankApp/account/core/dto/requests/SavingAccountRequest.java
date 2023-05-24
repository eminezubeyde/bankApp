package com.example.bankApp.account.core.dto.requests;

import com.example.bankApp.account.entity.enums.CurrencyType;
import com.example.bankApp.account.entity.enums.Maturity;
import com.example.bankApp.account.entity.enums.PurposeSaving;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SavingAccountRequest {
    private String parentAccountNumber;
    private String accountName;
    private CurrencyType currencyType;
    private PurposeSaving purposeSaving;
    private BigDecimal targetAmount;
    private Maturity maturity;
    private BigDecimal openingBalance;
}
