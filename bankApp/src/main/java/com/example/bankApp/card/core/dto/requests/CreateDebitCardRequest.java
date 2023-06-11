package com.example.bankApp.card.core.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDebitCardRequest {
    private String password;
    private int checkingAccountId;
}
