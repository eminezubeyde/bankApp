package com.example.bankApp.transfer.core.dto;

import com.example.bankApp.account.entity.enums.CurrencyType;
import com.example.bankApp.transfer.entitiy.enums.TransferType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class TransferDto {
    private int id;
    private String fromIban;
    private String toIban;
    private BigDecimal amount;
    private String description;
    private LocalDateTime processTime;
    private TransferType transferType;
    private CurrencyType currencyType;
}
