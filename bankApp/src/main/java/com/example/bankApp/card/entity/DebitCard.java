package com.example.bankApp.card.entity;

import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.card.entity.enums.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class DebitCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;
    private String password;
    private String cvv;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private CheckingAccount checkingAccount;
}

