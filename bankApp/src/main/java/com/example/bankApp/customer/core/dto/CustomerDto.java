package com.example.bankApp.customer.core.dto;

import com.example.bankApp.account.entity.CheckingAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CustomerDto {
    private int id;
    private int checkingAccountId;
    private int savingAccountId;
    private String identityNumber;
    private String name;
    private String surname;
    private String password;
    private String telephone;
    private BigDecimal income;
    private Date birthDay;
    private CheckingAccount checkingAccount;
}
