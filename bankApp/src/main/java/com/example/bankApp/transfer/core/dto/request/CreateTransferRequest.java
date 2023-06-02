package com.example.bankApp.transfer.core.dto.request;

import com.example.bankApp.account.entity.enums.CurrencyType;
import com.example.bankApp.transfer.entitiy.enums.TransferType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CreateTransferRequest {
    private String fromIban;
    private String toIban;
    private BigDecimal amount;
    private String description;
    private TransferType transferType;
    private CurrencyType currencyType;
}
