package com.example.bankApp.account.core.dto;

import com.example.bankApp.account.entity.base.Account;
import com.example.bankApp.common.core.entity.ActionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountActivityDto {
    private int id;
    private String crossAccount;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private BigDecimal availableBalance;
    private ActionStatus actionStatus;

}
