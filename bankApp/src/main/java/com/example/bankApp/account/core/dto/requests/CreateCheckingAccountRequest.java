package com.example.bankApp.account.core.dto.requests;

import com.example.bankApp.account.entity.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCheckingAccountRequest {
    private CurrencyType currencyType;
    private String bankCode;
    private String branchCode;
    private String branchName;
    private double balance;
    private String accountName;
    private int customerId;

}
