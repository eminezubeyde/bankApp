package com.example.bankApp.common.core.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AccountHelper {
    public BigDecimal getSuccessRate(BigDecimal openingBalance, BigDecimal targetAmount) {
        BigDecimal hundred = BigDecimal.valueOf(100);
        return openingBalance
                .multiply(hundred)
                .divide(targetAmount);
    }

}
