package com.example.bankApp.card.core.dto;

import com.example.bankApp.card.entity.enums.CardStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DebitCardResponse {
    private int id;
    private int checkingAccountId;
    private String cardNumber;
    private String cvv;
    private LocalDateTime expiryDate;
    private String password;
    private CardStatus cardStatus;

}
