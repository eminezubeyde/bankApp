package com.example.bankApp.common.core.utils;

import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
    public double getSuccessRate(double openingBalance, double targetAmount) {
        return (openingBalance * 100) / targetAmount;
    }
}
