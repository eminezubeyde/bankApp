package com.example.bankApp.account.entity.base;

import com.example.bankApp.common.core.entity.ActionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String crossAccount; //karşı hesap İBAN
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private BigDecimal availableBalance;
    @Enumerated(value = EnumType.STRING)
    private ActionStatus actionStatus;
    @ManyToOne
    private Account account;

}
